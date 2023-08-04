package com.modern.app.infrastructure.events;

import com.modern.app.application.outputs.NotificationType;
import com.modern.app.application.outputs.OnboardingPlanAssignmentNotification;
import com.modern.app.infrastructure.dto.OnboardingNotificationEvent;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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

        OnboardingNotificationEvent onboardingNotificationEvent = new OnboardingNotificationEvent(notificationType, onboardingPlanId, actorsToNotify);
        kafkaTemplate.send(onboardingEventTopic, onboardingNotificationEvent);
    }
}
