package com.modern.app.infrastructure.events;

import com.modern.app.application.outputs.NotificationType;
import com.modern.app.application.outputs.OnboardingPlanAssignmentNotification;
import com.modern.app.infrastructure.dto.OnboardingNotificationEvent;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaOnboardingPlanAssignmentNotification implements OnboardingPlanAssignmentNotification {

    private final KafkaTemplate<String, OnboardingNotificationEvent> kafkaTemplate;

    @Value("app.events.onboarding.topic")
    private String onboardingEventTopic;

    public KafkaOnboardingPlanAssignmentNotification(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notifyRelevantParties(NotificationType notificationType, String onboardingPlanId, List<Long> actorsToNotify) {

        Map<String, String> payload = new HashMap<>();
        payload.put("planId", onboardingPlanId);
        payload.put("recipients", Strings.join(actorsToNotify, ','));

        OnboardingNotificationEvent onboardingNotificationEvent = new OnboardingNotificationEvent(notificationType, payload);
        kafkaTemplate.send(onboardingEventTopic, onboardingNotificationEvent);
    }
}
