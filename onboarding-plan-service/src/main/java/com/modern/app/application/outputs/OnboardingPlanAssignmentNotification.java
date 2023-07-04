package com.modern.app.application.outputs;

import java.util.List;

public interface OnboardingPlanAssignmentNotification {

    void notifyRelevantParties(NotificationType notificationType, String onboardingPlanId, List<Long> actorsToNotify);
}
