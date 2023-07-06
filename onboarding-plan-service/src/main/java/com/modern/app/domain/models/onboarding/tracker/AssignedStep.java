package com.modern.app.domain.models.onboarding.tracker;

import lombok.Getter;


@Getter
public class AssignedStep {

    private String title;

    private String instructions;

    private boolean isCompleted;

    public AssignedStep(String title, String instructions, boolean isCompleted) {
        this.title = title;
        this.instructions = instructions;
        this.isCompleted = isCompleted;
    }

    public AssignedStep(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
        this.isCompleted = false;
    }

    public void completeStep() {
        isCompleted = true;
    }

}
