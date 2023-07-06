package com.modern.app.domain.models.onboarding.tracker;

import com.modern.app.domain.models.onboarding.plan.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class AssignedTask {

    private List<AssignedStep> assignedSteps;
    private String taskName;
    private boolean isCompleted;

    public AssignedTask(List<AssignedStep> assignedSteps, String taskName, boolean isCompleted) {
        this.assignedSteps = assignedSteps;
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public AssignedTask(List<Step> steps, String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
        this.assignedSteps = mapStepsToAssignedSteps(steps);
    }

    private List<AssignedStep> mapStepsToAssignedSteps(List<Step> steps) {
        return steps.stream().map(s -> new AssignedStep(s.title(), s.instructions())).toList();
    }


    public void completeSteps(List<String> stepNames) {
        assignedSteps.stream().filter(step -> stepNames.contains(step.getTitle())).forEach(AssignedStep::completeStep);

        // if all steps are completed, complete task
        if (assignedSteps.stream().allMatch(AssignedStep::isCompleted)) {
            this.isCompleted = true;
        }
    }
}
