package com.modern.app.domain.models.notification.configuration;

import com.modern.app.domain.models.notification.NotificationType;

public record NotificationConfig(String name, String handlerName, NotificationType notificationType, String template) {

}
