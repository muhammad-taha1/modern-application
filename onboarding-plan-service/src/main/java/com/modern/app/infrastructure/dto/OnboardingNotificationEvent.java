package com.modern.app.infrastructure.dto;

import com.modern.app.application.outputs.NotificationType;

import java.util.List;
import java.util.Map;

public record OnboardingNotificationEvent(NotificationType notificationType, Map<String, String> payload) {
}
