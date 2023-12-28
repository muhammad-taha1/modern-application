package com.modern.app.application.inputs;

import com.modern.app.application.outputs.NotificationConfigRepository;
import com.modern.app.domain.models.notification.configuration.NotificationConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationConfigService {

    private NotificationConfigRepository notificationConfigRepository;

    public NotificationConfig getNotificationConfigByName(String name) {
        return notificationConfigRepository.getByName(name);
    }

    public void saveNotificationConfig(NotificationConfig notificationConfig) {
        notificationConfigRepository.save(notificationConfig);
    }
}
