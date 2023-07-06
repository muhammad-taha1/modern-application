package com.modern.app.application.outputs;

import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;

public interface OnboardingPlanAssignmentRepository {

    String save(AssignedOnboardingPlan assignedOnboardingPlan);
    AssignedOnboardingPlan getById(String onboardingPlanAssignmentId) throws OnboardingPlanException;
}
