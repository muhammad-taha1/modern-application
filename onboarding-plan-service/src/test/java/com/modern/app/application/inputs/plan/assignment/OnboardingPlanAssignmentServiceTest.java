package com.modern.app.application.inputs.plan.assignment;

import com.modern.app.application.outputs.NotificationType;
import com.modern.app.application.outputs.OnboardingPlanAssignmentNotification;
import com.modern.app.application.outputs.OnboardingPlanAssignmentRepository;
import com.modern.app.application.outputs.OnboardingPlanRepository;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OnboardingPlanAssignmentServiceTest {


    private OnboardingPlanAssignmentService onboardingPlanAssignmentService;
    private OnboardingPlanRepository onboardingPlanRepository;
    private OnboardingPlanAssignmentRepository onboardingPlanAssignmentRepository;
    private OnboardingPlanAssignmentNotification onboardingPlanAssignmentNotification;

    @BeforeEach
    void setUp() {
        onboardingPlanRepository = mock(OnboardingPlanRepository.class);
        onboardingPlanAssignmentRepository = mock(OnboardingPlanAssignmentRepository.class);
        onboardingPlanAssignmentNotification = mock(OnboardingPlanAssignmentNotification.class);
        onboardingPlanAssignmentService = new OnboardingPlanAssignmentService(
                onboardingPlanRepository,
                onboardingPlanAssignmentRepository,
                onboardingPlanAssignmentNotification
        );
    }

    @Test
    void assignOnboardingPlanShouldAssignPlanAndNotifyRelevantParties() throws OnboardingPlanException {
        // Arrange
        long assigneeId = 1;
        long pointOfContactId = 2;
        String onboardingPlanId = "abc";
        long assignerId = 4;
        LocalDate dueDate = LocalDate.now();
        OnboardingPlan onboardingPlan = mock(OnboardingPlan.class);
        when(onboardingPlanRepository.getById(onboardingPlanId)).thenReturn(onboardingPlan);

        // Act
        onboardingPlanAssignmentService.assignOnboardingPlan(assigneeId, pointOfContactId, onboardingPlanId, assignerId, dueDate);

        // Assert
        verify(onboardingPlanAssignmentNotification).notifyRelevantParties(
                eq(NotificationType.ONBOARDING_PLAN_ASSIGNED),
                any(),
                eq(List.of(assigneeId))
        );
    }

    @Test
    void isDueShouldReturnTrueAndNotifyRelevantPartiesWhenPlanIsDue() throws OnboardingPlanException {
        // Arrange
        String assignedOnboardingPlanId = "1";
        AssignedOnboardingPlan assignedOnboardingPlan = mock(AssignedOnboardingPlan.class);
        when(assignedOnboardingPlan.isDue()).thenReturn(true);
        when(onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId)).thenReturn(assignedOnboardingPlan);

        // Act
        boolean isDue = onboardingPlanAssignmentService.isDue(assignedOnboardingPlanId);

        // Assert
        assertTrue(isDue);
        verify(onboardingPlanAssignmentNotification).notifyRelevantParties(
                NotificationType.ONBOARDING_PLAN_OVERDUE,
                assignedOnboardingPlan.getOnboardingPlanId(),
                List.of(
                        assignedOnboardingPlan.getAssignerId(),
                        assignedOnboardingPlan.getAssigneeId(),
                        assignedOnboardingPlan.getPointOfContactId()
                )
        );
    }

    @Test
    void isDueShouldReturnFalseWhenPlanIsNotDue() throws OnboardingPlanException {
        // Arrange
        String assignedOnboardingPlanId = "1";
        AssignedOnboardingPlan assignedOnboardingPlan = mock(AssignedOnboardingPlan.class);
        when(assignedOnboardingPlan.isDue()).thenReturn(false);
        when(onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId)).thenReturn(assignedOnboardingPlan);

        // Act
        boolean isDue = onboardingPlanAssignmentService.isDue(assignedOnboardingPlanId);

        // Assert
        assertFalse(isDue);
        verifyNoInteractions(onboardingPlanAssignmentNotification);
    }

    @Test
    void completeStepsShouldCompleteStepsAndNotifyRelevantPartiesWhenPlanIsCompleted() throws OnboardingPlanException {
        // Arrange
        String assignedOnboardingPlanId = "1";
        List<String> stepNames = List.of("Step 1", "Step 2");
        String taskName = "Task 1";
        AssignedOnboardingPlan assignedOnboardingPlan = mock(AssignedOnboardingPlan.class);
        when(assignedOnboardingPlan.isPlanCompleted()).thenReturn(true);
        when(onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId)).thenReturn(assignedOnboardingPlan);

        // Act
        onboardingPlanAssignmentService.completeSteps(assignedOnboardingPlanId, stepNames, taskName);

        // Assert
        verify(assignedOnboardingPlan).completeSteps(stepNames, taskName);
        verify(onboardingPlanAssignmentRepository).save(assignedOnboardingPlan);
        verify(onboardingPlanAssignmentNotification).notifyRelevantParties(
                NotificationType.ONBOARDING_PLAN_COMPLETED,
                assignedOnboardingPlan.getOnboardingPlanId(),
                List.of(
                        assignedOnboardingPlan.getAssignerId(),
                        assignedOnboardingPlan.getAssigneeId(),
                        assignedOnboardingPlan.getPointOfContactId()
                )
        );
    }

    @Test
    void completeStepsShouldCompleteStepsButNotNotifyRelevantPartiesWhenPlanIsNotCompleted() throws OnboardingPlanException {
        // Arrange
        String assignedOnboardingPlanId = "1";
        List<String> stepNames = List.of("Step 1", "Step 2");
        String taskName = "Task 1";
        AssignedOnboardingPlan assignedOnboardingPlan = mock(AssignedOnboardingPlan.class);
        when(assignedOnboardingPlan.isPlanCompleted()).thenReturn(false);
        when(onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId)).thenReturn(assignedOnboardingPlan);

        // Act
        onboardingPlanAssignmentService.completeSteps(assignedOnboardingPlanId, stepNames, taskName);

        // Assert
        verify(assignedOnboardingPlan).completeSteps(stepNames, taskName);
        verify(onboardingPlanAssignmentRepository).save(assignedOnboardingPlan);
        verifyNoInteractions(onboardingPlanAssignmentNotification);
    }
}