package com.modern.app.application.inputs;

import com.modern.app.application.outputs.OnboardingPlanRepository;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class OnboardingPlanManagementService implements OnboardingPlanManagement {

    private final OnboardingPlanRepository onboardingPlanRepository;

    public OnboardingPlanManagementService(OnboardingPlanRepository onboardingPlanRepository) {
        this.onboardingPlanRepository = onboardingPlanRepository;
    }

    @Override
    public String createNewOnboardingPlan(String planName, long createdById) {
        OnboardingPlan onboardingPlan = new OnboardingPlan(Strings.EMPTY, planName, createdById);
        return onboardingPlanRepository.save(onboardingPlan);
    }

    @Override
    public void addTaskToPlan(String onboardingPlanId, String taskName) throws OnboardingPlanException {
        OnboardingPlan onboardingPlan = onboardingPlanRepository.getById(onboardingPlanId);
        onboardingPlan.createNewTask(taskName);
        onboardingPlanRepository.save(onboardingPlan);
    }

    @Override
    public void removeTaskFromPlan(String onboardingPlanId, String taskName) throws OnboardingPlanException {
        OnboardingPlan onboardingPlan = onboardingPlanRepository.getById(onboardingPlanId);
        onboardingPlan.removeTask(taskName);
        onboardingPlanRepository.save(onboardingPlan);
    }

    @Override
    public void addStepToTask(String onboardingPlanId, String taskName, String stepName, String stepInstructions) throws OnboardingPlanException {
        OnboardingPlan onboardingPlan = onboardingPlanRepository.getById(onboardingPlanId);
        onboardingPlan.addStepToTask(taskName, stepName, stepInstructions);
        onboardingPlanRepository.save(onboardingPlan);
    }

    @Override
    public void updateStepInTask(String onboardingPlanId, String taskName, String stepOldName, String stepName, String stepInstructions) throws OnboardingPlanException {
        OnboardingPlan onboardingPlan = onboardingPlanRepository.getById(onboardingPlanId);
        onboardingPlan.updateStepDetailsForTask(taskName, stepOldName, stepName, stepInstructions);
        onboardingPlanRepository.save(onboardingPlan);
    }

    @Override
    public void removeStepFromTask(String onboardingPlanId, String taskName, String stepName) throws OnboardingPlanException {
        OnboardingPlan onboardingPlan = onboardingPlanRepository.getById(onboardingPlanId);
        onboardingPlan.removeStepFromTask(taskName, stepName);
        onboardingPlanRepository.save(onboardingPlan);
    }

    @Override
    public void removePlan(String onboardingPlanId) {
        onboardingPlanRepository.remove(onboardingPlanId);
    }

    @Override
    public OnboardingPlan getById(String onboardingPlanId) throws OnboardingPlanException {
        return onboardingPlanRepository.getById(onboardingPlanId);
    }
}
