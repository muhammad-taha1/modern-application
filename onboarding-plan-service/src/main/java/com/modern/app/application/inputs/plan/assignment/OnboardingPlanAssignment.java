package com.modern.app.application.inputs.plan.assignment;

import com.modern.app.domain.exceptions.OnboardingPlanException;

import java.time.LocalDate;
import java.util.List;

public interface OnboardingPlanAssignment {

    String assignOnboardingPlan(long assigneeId, long pointOfContactId, String onboardingPlanId, long assignerId, LocalDate dueDate) throws OnboardingPlanException;

    boolean isDue(long assignedOnboardingPlanId);

    void completeSteps(long assignedOnboardingPlanId, List<String> stepNames, String taskName);
}
