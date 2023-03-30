package com.transport.uberApp.notification;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
