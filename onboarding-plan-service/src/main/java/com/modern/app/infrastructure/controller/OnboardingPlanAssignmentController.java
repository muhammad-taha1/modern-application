package com.modern.app.infrastructure.controller;

import com.modern.app.application.inputs.plan.assignment.OnboardingPlanAssignment;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnboardingPlanAssignmentController {

    private OnboardingPlanAssignment onboardingPlanAssignment;

    public OnboardingPlanAssignmentController(OnboardingPlanAssignment onboardingPlanAssignment) {
        this.onboardingPlanAssignment = onboardingPlanAssignment;
    }
}
