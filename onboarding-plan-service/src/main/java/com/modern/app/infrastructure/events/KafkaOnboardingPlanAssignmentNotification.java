package com.modern.app.infrastructure.events;

import com.modern.app.application.outputs.NotificationType;
import com.modern.app.application.outputs.OnboardingPlanAssignmentNotification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaOnboardingPlanAssignmentNotification implements OnboardingPlanAssignmentNotification {
    @Override
    public void notifyRelevantParties(NotificationType notificationType, String onboardingPlanId, List<Long> actorsToNotify) {

    }
}
