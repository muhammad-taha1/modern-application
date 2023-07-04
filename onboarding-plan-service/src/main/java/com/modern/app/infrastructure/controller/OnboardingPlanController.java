package com.modern.app.infrastructure.controller;

import com.modern.app.application.inputs.OnboardingPlanManagement;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.infrastructure.dto.StepRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboarding-plan")
public class OnboardingPlanController {

    private OnboardingPlanManagement onboardingPlanManagement;

    public OnboardingPlanController(OnboardingPlanManagement onboardingPlanManagement) {
        this.onboardingPlanManagement = onboardingPlanManagement;
    }

    @PostMapping
    @Operation(summary = "create a new onboarding plan")
    public ResponseEntity<String> createOnboardingPlan(@RequestParam String planName, @RequestParam long creatorId) {
        return ResponseEntity.accepted().body(onboardingPlanManagement.createNewOnboardingPlan(planName, creatorId));
    }

    @PostMapping("/{onboardingPlanId}/task")
    @Operation(summary = "create a task for plan")
    public ResponseEntity createTaskForPlan(@PathVariable String onboardingPlanId, @RequestParam String taskName) throws OnboardingPlanException {
        onboardingPlanManagement.addTaskToPlan(onboardingPlanId, taskName);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{onboardingPlanId}/task/{taskName}")
    @Operation(summary = "Delete task from plan")
    public ResponseEntity removeTaskFromPlan(@PathVariable String onboardingPlanId, @PathVariable String taskName) throws OnboardingPlanException {
        onboardingPlanManagement.removeTaskFromPlan(onboardingPlanId, taskName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{onboardingPlanId}/task/{taskName}")
    @Operation(summary = "add step in a task")
    public ResponseEntity addStepToTask(@PathVariable String onboardingPlanId, @PathVariable String taskName, @RequestBody StepRequest stepRequest) throws OnboardingPlanException {
        onboardingPlanManagement.addStepToTask(onboardingPlanId, taskName, stepRequest.stepTitle(), stepRequest.stepInstructions());
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/{onboardingPlanId}/task/{taskName}/step")
    @Operation(summary = "update step in a task")
    public ResponseEntity updateStepInTask(
            @PathVariable String onboardingPlanId,
            @PathVariable String taskName,
            @RequestParam String oldStepName,
            @RequestBody StepRequest stepRequest) throws OnboardingPlanException {
        onboardingPlanManagement.updateStepInTask(onboardingPlanId, taskName, oldStepName, stepRequest.stepTitle(), stepRequest.stepInstructions());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{onboardingPlanId}/task/{taskName}/step")
    @Operation(summary = "remove step from task")
    public ResponseEntity removeStepFromTask(
            @PathVariable String onboardingPlanId,
            @PathVariable String taskName,
            @RequestParam String stepName) throws OnboardingPlanException {
        onboardingPlanManagement.removeStepFromTask(onboardingPlanId, taskName, stepName);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{onboardingPlanId}")
    @Operation(summary = "delete onboarding plan")
    public ResponseEntity deleteOnboardingPlan(
            @PathVariable String onboardingPlanId) {
        onboardingPlanManagement.removePlan(onboardingPlanId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{onboardingPlanId}")
    @Operation(summary = "get onboarding plan")
    public ResponseEntity<OnboardingPlan> getOnboardingPlan(
            @PathVariable String onboardingPlanId) throws OnboardingPlanException {
        return ResponseEntity.ok(onboardingPlanManagement.getById(onboardingPlanId));
    }
}
