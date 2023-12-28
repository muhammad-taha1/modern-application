package com.modern.app.infrastructure.event.listeners;

import com.modern.app.application.inputs.InboundNotificationTranslator;
import com.modern.app.domain.models.notification.event.InboundEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaEventListener {

    private InboundNotificationTranslator inboundNotificationTranslator;

    @KafkaListener(topics = "business-events", groupId = "notification-listener")
    public void listenToBusinessEvents(InboundEvent inboundEvent) {
        inboundNotificationTranslator.parseIncomingNotification(inboundEvent);
    }
}
