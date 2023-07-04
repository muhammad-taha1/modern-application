package com.modern.app.domain.models;

import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.plan.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OnboardingPlanTest {

    private OnboardingPlan onboardingPlan;

    @BeforeEach
    public void setup() {
        onboardingPlan = new OnboardingPlan("", "Plan 1", 1);
    }

    @Test
    public void createNewTaskSuccessfully() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");

        List<Task> tasks = onboardingPlan.tasks();
        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).name());
    }

    @Test
    public void createNewTaskAlreadyExistsThrowsException() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");

        assertThrows(OnboardingPlanException.class, () -> onboardingPlan.createNewTask("Task 1"));
    }

    @Test
    public void removeTaskSuccessfully() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");
        onboardingPlan.removeTask("Task 1");

        List<Task> tasks = onboardingPlan.tasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void addStepToTaskSuccessfully() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");
        onboardingPlan.addStepToTask("Task 1", "step 1", "do xyz");
        onboardingPlan.addStepToTask("Task 1", "step 2", "do abc");

        List<Task> tasks = onboardingPlan.tasks();
        assertEquals("Task 1", tasks.get(0).name());
        assertEquals(2, tasks.get(0).steps().size());
        assertEquals("step 1", tasks.get(0).steps().get(0).title());
        assertEquals("step 2", tasks.get(0).steps().get(1).title());
    }


    @Test
    public void addStepToNonExistingTask() throws OnboardingPlanException {
        assertThrows(OnboardingPlanException.class, () -> onboardingPlan.addStepToTask("Task 1", "step 1", "do xyz"));
    }

    @Test
    public void UpdateStepInTask() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");

        onboardingPlan.addStepToTask("Task 1", "step 1", "do xyz");
        onboardingPlan.addStepToTask("Task 1", "step 2", "do abc");

        onboardingPlan.updateStepDetailsForTask("Task 1", "step 1", "step a", "do true");

        List<Task> tasks = onboardingPlan.tasks();
        assertEquals("Task 1", tasks.get(0).name());
        assertEquals(2, tasks.get(0).steps().size());
        assertEquals("step a", tasks.get(0).steps().get(0).title());
        assertEquals("step 2", tasks.get(0).steps().get(1).title());
        assertEquals("do true", tasks.get(0).steps().get(0).instructions());
        assertEquals("do abc", tasks.get(0).steps().get(1).instructions());
    }


    @Test
    public void removeStepFromTask() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");
        onboardingPlan.addStepToTask("Task 1", "step 1", "do xyz");
        onboardingPlan.addStepToTask("Task 1", "step 2", "do abc");
        onboardingPlan.removeStepFromTask("Task 1", "step 2");

        List<Task> tasks = onboardingPlan.tasks();
        assertEquals("Task 1", tasks.get(0).name());
        assertEquals(1, tasks.get(0).steps().size());
        assertEquals("step 1", tasks.get(0).steps().get(0).title());
    }

    @Test
    public void removeStepFromTaskDeletesTaskIfItsEmpty() throws OnboardingPlanException {
        onboardingPlan.createNewTask("Task 1");
        onboardingPlan.addStepToTask("Task 1", "step 1", "do xyz");
        onboardingPlan.removeStepFromTask("Task 1", "step 1");

        List<Task> tasks = onboardingPlan.tasks();
        assertEquals(0, tasks.size());
    }
}