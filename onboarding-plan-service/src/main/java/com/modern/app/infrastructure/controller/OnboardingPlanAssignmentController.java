package com.modern.app.infrastructure.controller;

import com.modern.app.application.inputs.plan.assignment.OnboardingPlanAssignment;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;
import com.modern.app.infrastructure.dto.AssignedOnboardingRequest;
import com.modern.app.infrastructure.dto.CompleteStepsRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class OnboardingPlanAssignmentController {

    private OnboardingPlanAssignment onboardingPlanAssignment;

    public OnboardingPlanAssignmentController(OnboardingPlanAssignment onboardingPlanAssignment) {
        this.onboardingPlanAssignment = onboardingPlanAssignment;
    }

    @PostMapping
    @Operation(summary = "assign a plan to user")
    public ResponseEntity<String> assignOnboardingPlan(
            @RequestBody AssignedOnboardingRequest assignedOnboardingRequest) throws OnboardingPlanException {


        return ResponseEntity.ok(
                onboardingPlanAssignment.assignOnboardingPlan(
                        assignedOnboardingRequest.getAssigneeId(),
                        assignedOnboardingRequest.getPointOfContactId(),
                        assignedOnboardingRequest.getOnboardingPlanId(),
                        assignedOnboardingRequest.getAssignerId(),
                        assignedOnboardingRequest.getDueDate()));
    }

    @GetMapping("/{assignedPlanId}")
    @Operation(summary = "Get assigned plan details")
    public ResponseEntity<AssignedOnboardingPlan> getAssignedOnboardingPlan(@PathVariable String assignedPlanId) throws OnboardingPlanException {
        return ResponseEntity.ok(onboardingPlanAssignment.getAssignedOnboardingPlanById(assignedPlanId));
    }

    @GetMapping("/isDue/{assignedPlanId}")
    @Operation(summary = "Returns true if plan is overdue")
    public ResponseEntity<Boolean> isOnboardingPlanDue(@PathVariable String assignedPlanId) throws OnboardingPlanException {
        return ResponseEntity.ok(onboardingPlanAssignment.isDue(assignedPlanId));
    }

    @PostMapping("/completeSteps")
    @Operation(summary = "mark several steps as completed")
    public ResponseEntity completeOnboardingSteps(@RequestBody CompleteStepsRequest completeStepsRequest) throws OnboardingPlanException {
        onboardingPlanAssignment.completeSteps(
                completeStepsRequest.assignedOnboardingPlanId(),
                completeStepsRequest.stepNames(),
                completeStepsRequest.taskName());

        return ResponseEntity.accepted().build();
    }

}
