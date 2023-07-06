package com.modern.app.domain.models;

import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignedOnboardingPlanTest {

    private AssignedOnboardingPlan assignedOnboardingPlan;
    private OnboardingPlan onboardingPlan;


    @BeforeEach
    public void setup() throws OnboardingPlanException {
        onboardingPlan = new OnboardingPlan("", "Plan 1", 1);
        onboardingPlan.createNewTask("Task 1");
        onboardingPlan.createNewTask("Task 2");

        onboardingPlan.addStepToTask("Task 1", "step 1", "do xyz");
        onboardingPlan.addStepToTask("Task 1", "step 2", "do abc");
        onboardingPlan.addStepToTask("Task 2", "step 1", "do yrt");

        assignedOnboardingPlan = new AssignedOnboardingPlan(
                123L,
                504L,
                "test",
                156L,
                onboardingPlan.tasks(),
                LocalDate.of(1999, 10, 1));
    }

    @Test
    public void planStepsCompletedSuccessfully() {
        assignedOnboardingPlan.completeSteps(List.of("step 1"), "Task 1");

        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(0).isCompleted());
        assertFalse(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(1).isCompleted());
    }

    @Test
    public void planTaskCompletedSuccessfully() {
        assignedOnboardingPlan.completeSteps(List.of("step 1", "step 2"), "Task 1");

        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(0).isCompleted());
        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(1).isCompleted());
        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).isCompleted());
        assertFalse(assignedOnboardingPlan.isPlanCompleted());
    }


    @Test
    public void planCompletedSuccessfully() {
        assignedOnboardingPlan.completeSteps(List.of("step 1", "step 2"), "Task 1");
        assignedOnboardingPlan.completeSteps(List.of("step 1"), "Task 2");

        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(0).isCompleted());
        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(1).isCompleted());
        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(1).getAssignedSteps().get(0).isCompleted());
        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(0).isCompleted());
        assertTrue(assignedOnboardingPlan.getAssignedTasks().get(1).isCompleted());
        assertTrue(assignedOnboardingPlan.isPlanCompleted());
    }


    @Test
    public void invalidStepCompletionHasNoImpact() {
        assignedOnboardingPlan.completeSteps(List.of("step 4"), "Task 1");

        assertFalse(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(0).isCompleted());
        assertFalse(assignedOnboardingPlan.getAssignedTasks().get(0).getAssignedSteps().get(1).isCompleted());
        assertFalse(assignedOnboardingPlan.getAssignedTasks().get(0).isCompleted());
    }

    @Test
    public void dueDateCheckedSuccessfully() {
        assertTrue(assignedOnboardingPlan.isDue());

        AssignedOnboardingPlan assignedOnboardingPlanCopy = new AssignedOnboardingPlan(
                126L,
                504L,
                "test",
                156L,
                onboardingPlan.tasks(),
                LocalDate.of(2060, 10, 1));

        assertFalse(assignedOnboardingPlanCopy.isDue());
    }

}
