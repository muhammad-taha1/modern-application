package com.modern.app.infrastructure.dto;

import com.modern.app.application.outputs.NotificationType;

import java.util.List;

public record OnboardingNotificationEvent(NotificationType notificationType, String onboardingPlanId, List<Long> actorsToNotify) {
}
