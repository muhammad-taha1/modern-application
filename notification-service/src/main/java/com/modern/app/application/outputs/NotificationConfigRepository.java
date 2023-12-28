package com.modern.app.application.outputs;


import com.modern.app.domain.models.notification.configuration.NotificationConfig;

public interface NotificationConfigRepository {

    NotificationConfig getByName(String name);

    NotificationConfig save(NotificationConfig notificationConfig);
}
