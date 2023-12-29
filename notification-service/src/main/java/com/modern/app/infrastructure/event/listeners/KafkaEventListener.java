package com.modern.app.infrastructure.event.listeners;

import com.modern.app.application.inputs.InboundNotificationTranslator;
import com.modern.app.domain.models.notification.event.InboundEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaEventListener {

    private InboundNotificationTranslator inboundNotificationTranslator;

    @KafkaListener(topics = "business-events", groupId = "notification-listener")
    public void listenToBusinessEvents(Map<String, Object> inboundEventMap) {
        try {
            log.info("received new event: {}", inboundEventMap);

            InboundEvent inboundEvent = new InboundEvent(inboundEventMap.get("notificationType").toString(), (Map<String, String>) inboundEventMap.get("payload"));
            inboundNotificationTranslator.parseIncomingNotification(inboundEvent);
        }
        catch (Exception e){
            log.error("Couldnt parse message", e);
        }
    }
}
