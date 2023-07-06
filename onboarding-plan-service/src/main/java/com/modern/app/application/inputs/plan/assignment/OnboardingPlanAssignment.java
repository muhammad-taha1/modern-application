package com.modern.app.application.inputs.plan.assignment;

import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;

import java.time.LocalDate;
import java.util.List;

public interface OnboardingPlanAssignment {

    String assignOnboardingPlan(long assigneeId, long pointOfContactId, String onboardingPlanId, long assignerId, LocalDate dueDate) throws OnboardingPlanException;

    boolean isDue(String assignedOnboardingPlanId) throws OnboardingPlanException;

    void completeSteps(String assignedOnboardingPlanId, List<String> stepNames, String taskName) throws OnboardingPlanException;

    AssignedOnboardingPlan getAssignedOnboardingPlanById(String assignedOnboardingPlanId) throws OnboardingPlanException;
}
