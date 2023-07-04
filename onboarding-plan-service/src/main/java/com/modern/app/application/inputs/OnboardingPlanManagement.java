package com.modern.app.application.inputs;

import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;

public interface OnboardingPlanManagement {

    String createNewOnboardingPlan(String planName, long createdById);

    void addTaskToPlan(String onboardingPlanId, String taskName) throws OnboardingPlanException;

    void removeTaskFromPlan(String onboardingPlanId, String taskName) throws OnboardingPlanException;

    void addStepToTask(String onboardingPlanId, String taskName, String stepName, String stepInstructions) throws OnboardingPlanException;

    void updateStepInTask(String onboardingPlanId, String taskName, String stepOldName, String stepName, String stepInstructions) throws OnboardingPlanException;

    void removeStepFromTask(String onboardingPlanId, String taskName, String stepName) throws OnboardingPlanException;

    void removePlan(String onboardingPlanId);

    OnboardingPlan getById(String onboardingPlanId) throws OnboardingPlanException;
}
