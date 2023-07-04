package com.modern.app.domain.models.onboarding.tracker;

import com.modern.app.domain.models.onboarding.plan.Task;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class AssignedOnboardingPlan {

    private final long assigneeId;
    private final String onboardingPlanId;

    private boolean isPlanCompleted;

    private long pointOfContactId;

    private long assignerId;

    private List<AssignedTask> assignedTasks;

    private LocalDate dueDate;

    public AssignedOnboardingPlan(long assigneeId, long pointOfContactId, String onboardingPlanId, long assignerId, List<Task> tasks, LocalDate dueDate) {
        this.assigneeId = assigneeId;
        this.onboardingPlanId = onboardingPlanId;
        this.pointOfContactId = pointOfContactId;
        this.isPlanCompleted = false;
        this.assignedTasks = mapTasksToAssignedTasks(tasks);
        this.dueDate = dueDate;
        this.assignerId = assignerId;
    }

    private List<AssignedTask> mapTasksToAssignedTasks(List<Task> tasks) {
        return tasks.stream().map(t -> new AssignedTask(t.steps(), t.name())).toList();
    }

    public void completeSteps(List<String> stepNames, String taskName) {
        Optional<AssignedTask> assignedTaskOptional = assignedTasks.stream().filter(t -> t.getTaskName().equals(taskName)).findFirst();
        if (assignedTaskOptional.isEmpty()) return;

        AssignedTask assignedTask = assignedTaskOptional.get();

        assignedTask.completeSteps(stepNames);

        // if all tasks are completed, complete plan
        if (assignedTasks.stream().allMatch(AssignedTask::isCompleted)) {
            isPlanCompleted = true;
        }
    }

    public boolean isDue() {
        return LocalDate.now().isAfter(dueDate);
    }
}
