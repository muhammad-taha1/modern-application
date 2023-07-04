package com.modern.app.domain.models.onboarding.plan;

import com.modern.app.domain.exceptions.OnboardingPlanException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record OnboardingPlan(String id, String name, long createdBy, List<Task> tasks) {

    public OnboardingPlan(String id, String name, long createdBy) {
        this(id, name, createdBy, new ArrayList<>());
    }

    public void createNewTask(String taskName) throws OnboardingPlanException {
        if (tasks.stream().anyMatch(task -> task.name().equals(taskName))) {
            throw new OnboardingPlanException(String.format("onboarding plan already contains task of name %s", taskName));
        }

        Task task = new Task(taskName);
        tasks.add(task);
    }

    public void removeTask(String taskName) {
        tasks.removeIf(task -> task.name().equals(taskName));
    }

    public void addStepToTask(String taskName, String stepName, String stepInstructions) throws OnboardingPlanException {
        Task task = getTaskWithName(taskName);
        task.addStep(stepName, stepInstructions);
    }

    public void updateStepDetailsForTask(String taskName, String stepOldName, String stepName, String stepInstructions) throws OnboardingPlanException {
        Task task = getTaskWithName(taskName);
        task.updateStep(stepOldName, stepName, stepInstructions);
    }

    private Task getTaskWithName(String taskName) throws OnboardingPlanException {
        Optional<Task> taskToReturn = tasks.stream().filter(task -> task.name().equals(taskName)).findFirst();

        if (taskToReturn.isEmpty()) {
            throw new OnboardingPlanException(String.format("onboarding plan does not contain task %s", taskName));
        }

        return taskToReturn.get();
    }

    public void removeStepFromTask(String taskName, String stepName) throws OnboardingPlanException {
        Task task = getTaskWithName(taskName);
        task.removeStep(stepName);

        if (task.steps().isEmpty()) {
            removeTask(task.name());
        }
    }
}
