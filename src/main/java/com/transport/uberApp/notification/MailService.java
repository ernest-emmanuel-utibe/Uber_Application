package com.transport.uberApp.notification;

import com.transport.uberApp.data.dto.request.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
