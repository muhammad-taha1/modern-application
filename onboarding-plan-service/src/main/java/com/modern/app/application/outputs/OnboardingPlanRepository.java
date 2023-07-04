package com.modern.app.application.outputs;

import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;

public interface OnboardingPlanRepository {

    String save(OnboardingPlan onboardingPlan);

    OnboardingPlan getById(String onboardingPlanId) throws OnboardingPlanException;

    void remove(String onboardingPlanId);
}
