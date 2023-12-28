package com.modern.app.domain.models;

import com.modern.app.domain.models.notification.Notification;

import java.util.List;

public interface NotificationSender {
    Notification send(String content, List<String> recipients);
}
