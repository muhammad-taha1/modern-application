package com.modern.app.domain.models.notification;

import com.modern.app.domain.models.NotificationSender;
import com.modern.app.domain.models.notification.configuration.NotificationConfig;
import com.modern.app.domain.models.notification.event.InboundEvent;

import java.util.List;

public interface NotificationStrategy {

    default Notification send(NotificationConfig notificationConfig, InboundEvent inboundEvent) {

        String content = getContent(notificationConfig, inboundEvent);
        List<String> recipients = getRecipients(notificationConfig, inboundEvent);

        return getNotificationSender().send(content, recipients);
    }

    String getContent(NotificationConfig notificationConfig, InboundEvent inboundEvent);

    List<String> getRecipients(NotificationConfig notificationConfig, InboundEvent inboundEvent);

    NotificationSender getNotificationSender();
}
