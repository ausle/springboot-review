package com.asule.sms;

public class SmsClient {

    private final String accessKey;

    private final String secretKey;

    public SmsClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void send(String phone, String content) {
        System.out.println("send sms to " + phone + ": " + content);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
