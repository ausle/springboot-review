package com.asule.springbootreview.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AlarmUtilTest {

    @Test
    public void shouldSendAtMostOnceWithinOneMinute() throws Exception {
        RecordingAlarmUtil alarmUtil = new RecordingAlarmUtil();
        alarmUtil.setWebhookUrl("http://localhost/not-used");
        alarmUtil.start();

        alarmUtil.doAppend(loggingEvent("first error"));
        alarmUtil.doAppend(loggingEvent("second error"));

        Assert.assertTrue(alarmUtil.sent.await(2, TimeUnit.SECONDS));
        Assert.assertEquals(1, alarmUtil.sendCount.get());
        alarmUtil.stop();
    }

    @Test
    public void shouldIgnoreNonErrorLog() throws Exception {
        RecordingAlarmUtil alarmUtil = new RecordingAlarmUtil();
        alarmUtil.setWebhookUrl("http://localhost/not-used");
        alarmUtil.start();

        alarmUtil.doAppend(loggingEvent(Level.WARN, "warning"));
        Assert.assertFalse(alarmUtil.sent.await(200, TimeUnit.MILLISECONDS));
        Assert.assertEquals(0, alarmUtil.sendCount.get());
        alarmUtil.stop();
    }

    private ILoggingEvent loggingEvent(String message) {
        return loggingEvent(Level.ERROR, message);
    }

    private ILoggingEvent loggingEvent(Level level, String message) {
        LoggingEvent event = new LoggingEvent();
        event.setLevel(level);
        event.setLoggerName("com.asule.springbootreview.util.AlarmUtilTest");
        event.setThreadName(Thread.currentThread().getName());
        event.setMessage(message);
        event.setTimeStamp(System.currentTimeMillis());
        return event;
    }

    private static class RecordingAlarmUtil extends AlarmUtil {
        private final AtomicInteger sendCount = new AtomicInteger();
        private final CountDownLatch sent = new CountDownLatch(1);

        @Override
        protected void sendToWeCom(String content) {
            sendCount.incrementAndGet();
            sent.countDown();
        }
    }
}
