package com.modern.app.domain.models.onboarding.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Task(String name, List<Step> steps) {

    public Task(String name) {
        this(name, new ArrayList<>());
    }

    public void addStep(String stepName, String stepInstructions) {
        Step step = new Step(stepName, stepInstructions);
        steps.add(step);
    }

    public void removeStep(String stepName) {
        steps.removeIf(step -> step.title().equals(stepName));
    }

    public void updateStep(String stepOldName, String stepName, String stepInstructions) {
        Optional<Step> stepToUpdate = steps.stream().filter(step -> step.title().equals(stepOldName)).findFirst();

        if (stepToUpdate.isPresent()) {
            Step step = new Step(stepName, stepInstructions);
            steps.set(steps.indexOf(stepToUpdate.get()), step);
        }
    }
}
