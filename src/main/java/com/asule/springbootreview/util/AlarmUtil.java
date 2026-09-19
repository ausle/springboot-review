package com.asule.springbootreview.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.AppenderBase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 将 ERROR 级别的 Logback 日志发送到企业微信群机器人。
 *
 * <p>这个类由 logback-spring.xml 通过 class 属性创建，不是 Spring Bean。</p>
 */
public class AlarmUtil extends AppenderBase<ILoggingEvent> {

    private static final long RATE_LIMIT_NANOS = 60L * 1_000_000_000L;
    private static final long NOT_SENT = Long.MIN_VALUE;
    private static final int DEFAULT_TIMEOUT_MILLIS = 3_000;
    private static final int MAX_CONTENT_LENGTH = 4_000;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    private final AtomicLong lastSentNanos = new AtomicLong(NOT_SENT);

    private String webhookUrl;
    private int connectTimeoutMs = DEFAULT_TIMEOUT_MILLIS;
    private int readTimeoutMs = DEFAULT_TIMEOUT_MILLIS;
    private ExecutorService executor;

    @Override
    public void start() {
        if (isBlank(webhookUrl)) {
            addWarn("企业微信告警未启用：未配置 alarm.wecom.webhook");
            // 保持 Appender 处于 started 状态，避免每条 ERROR 日志再次产生“Appender 未启动”状态告警。
            super.start();
            return;
        }

        executor = Executors.newSingleThreadExecutor(new AlarmThreadFactory());
        super.start();
    }

    @Override
    public void stop() {
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
        super.stop();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (event == null || !event.getLevel().isGreaterOrEqual(Level.ERROR)
                || isBlank(webhookUrl) || executor == null) {
            return;
        }

        // 先占用发送配额，再异步发送，避免企业微信故障时每条错误日志都重复重试。
        if (!tryAcquireSendQuota()) {
            return;
        }

        final String content = formatEvent(event);
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sendToWeCom(content);
                }
            });
        } catch (RejectedExecutionException ignored) {
            // Appender 正在停止时，放弃本次告警即可，不能影响应用关闭。
        }
    }

    /**
     * 实际发送方法单独抽出来，便于单元测试覆盖限流逻辑。
     */
    protected void sendToWeCom(String content) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(webhookUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(connectTimeoutMs);
            connection.setReadTimeout(readTimeoutMs);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            String payload = "{\"msgtype\":\"text\",\"text\":{\"content\":\""
                    + escapeJson(content) + "\"}}";
            byte[] body = payload.getBytes(StandardCharsets.UTF_8);
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(body);
            }

            int responseCode = connection.getResponseCode();
            String responseBody = readResponseBody(connection, responseCode);
            boolean httpFailed = responseCode < 200 || responseCode >= 300;
            boolean weComFailed = !responseBody.isEmpty()
                    && !responseBody.replaceAll("\\s", "").contains("\"errcode\":0");
            if (httpFailed || weComFailed) {
                addWarn("企业微信告警发送失败，HTTP 状态码：" + responseCode
                        + "，响应：" + responseBody);
            }
        } catch (Exception e) {
            addWarn("企业微信告警发送异常：" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private boolean tryAcquireSendQuota() {
        long now = System.nanoTime();
        for (;;) {
            long lastSent = lastSentNanos.get();
            if (lastSent != NOT_SENT && now - lastSent < RATE_LIMIT_NANOS) {
                return false;
            }
            if (lastSentNanos.compareAndSet(lastSent, now)) {
                return true;
            }
        }
    }

    private String formatEvent(ILoggingEvent event) {
        StringBuilder content = new StringBuilder(512);
        content.append("【应用异常告警】\n")
                .append("时间：").append(TIME_FORMATTER.format(Instant.ofEpochMilli(event.getTimeStamp())))
                .append("\n级别：").append(event.getLevel())
                .append("\nLogger：").append(event.getLoggerName())
                .append("\n线程：").append(event.getThreadName())
                .append("\n消息：").append(event.getFormattedMessage());

        if (event.getThrowableProxy() != null) {
            content.append("\n异常：\n")
                    .append(ThrowableProxyUtil.asString(event.getThrowableProxy()));
        }

        if (content.length() > MAX_CONTENT_LENGTH) {
            content.setLength(MAX_CONTENT_LENGTH);
            content.append("\n...（日志过长，已截断）");
        }
        return content.toString();
    }

    private String readResponseBody(HttpURLConnection connection, int responseCode) throws IOException {
        InputStream inputStream = responseCode >= 400
                ? connection.getErrorStream() : connection.getInputStream();
        if (inputStream == null) {
            return "";
        }

        try (InputStream input = inputStream;
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[256];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            return new String(output.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    private String escapeJson(String value) {
        StringBuilder escaped = new StringBuilder(value.length() + 32);
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\\':
                    escaped.append("\\\\");
                    break;
                case '"':
                    escaped.append("\\\"");
                    break;
                case '\b':
                    escaped.append("\\b");
                    break;
                case '\f':
                    escaped.append("\\f");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                default:
                    if (c < 0x20) {
                        escaped.append(String.format("\\u%04x", (int) c));
                    } else {
                        escaped.append(c);
                    }
            }
        }
        return escaped.toString();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void setConnectTimeoutMs(int connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }

    public void setReadTimeoutMs(int readTimeoutMs) {
        this.readTimeoutMs = readTimeoutMs;
    }

    private static class AlarmThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "wecom-alarm-sender");
            thread.setDaemon(true);
            return thread;
        }
    }
}
