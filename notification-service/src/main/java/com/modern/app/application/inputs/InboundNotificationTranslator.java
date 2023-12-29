package com.modern.app.application.inputs;

import com.modern.app.application.outputs.NotificationConfigRepository;
import com.modern.app.domain.models.notification.Notification;
import com.modern.app.domain.models.notification.NotificationStrategy;
import com.modern.app.domain.models.notification.configuration.NotificationConfig;
import com.modern.app.domain.models.notification.event.InboundEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InboundNotificationTranslator {

    private final ApplicationContext context;

    private final NotificationConfigRepository notificationConfigRepository;

    public void parseIncomingNotification(InboundEvent event) {
        // fetch config from repo based on notification type
        String notificationFor = event.name();

        NotificationConfig notificationConfig = notificationConfigRepository.getByName(notificationFor);

        NotificationStrategy notificationSender = context.getBean(notificationConfig.notificationType().name(), NotificationStrategy.class);
        notificationSender.send(notificationConfig, event);
    }


}
