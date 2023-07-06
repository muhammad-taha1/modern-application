package com.modern.app.infrastructure.dto;

import java.util.List;

public record CompleteStepsRequest(String assignedOnboardingPlanId, List<String> stepNames, String taskName) {
}
