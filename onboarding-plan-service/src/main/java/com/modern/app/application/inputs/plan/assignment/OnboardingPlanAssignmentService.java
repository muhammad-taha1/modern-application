package com.modern.app.application.inputs.plan.assignment;

import com.modern.app.application.outputs.NotificationType;
import com.modern.app.application.outputs.OnboardingPlanAssignmentNotification;
import com.modern.app.application.outputs.OnboardingPlanAssignmentRepository;
import com.modern.app.application.outputs.OnboardingPlanRepository;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;

import java.time.LocalDate;
import java.util.List;

public class OnboardingPlanAssignmentService implements OnboardingPlanAssignment {

    private OnboardingPlanRepository onboardingPlanRepository;
    private OnboardingPlanAssignmentRepository onboardingPlanAssignmentRepository;

    private OnboardingPlanAssignmentNotification onboardingPlanAssignmentNotification;

    public OnboardingPlanAssignmentService(
            OnboardingPlanRepository onboardingPlanRepository,
            OnboardingPlanAssignmentRepository onboardingPlanAssignmentRepository,
            OnboardingPlanAssignmentNotification onboardingPlanAssignmentNotification) {
        this.onboardingPlanRepository = onboardingPlanRepository;
        this.onboardingPlanAssignmentRepository = onboardingPlanAssignmentRepository;
        this.onboardingPlanAssignmentNotification = onboardingPlanAssignmentNotification;
    }


    @Override
    public String assignOnboardingPlan(long assigneeId, long pointOfContactId, String onboardingPlanId, long assignerId, LocalDate dueDate) throws OnboardingPlanException {
        OnboardingPlan onboardingPlan = onboardingPlanRepository.getById(onboardingPlanId);

        AssignedOnboardingPlan assignedOnboardingPlan = new AssignedOnboardingPlan(
                assigneeId,
                pointOfContactId,
                onboardingPlanId,
                assignerId,
                onboardingPlan.tasks(),
                dueDate);

        String id = onboardingPlanAssignmentRepository.save(assignedOnboardingPlan);

        onboardingPlanAssignmentNotification.notifyRelevantParties(
                NotificationType.ONBOARDING_PLAN_ASSIGNED,
                assignedOnboardingPlan.getOnboardingPlanId(),
                List.of(assignedOnboardingPlan.getAssigneeId()));

        return id;
    }

    @Override
    public boolean isDue(long assignedOnboardingPlanId) {
        AssignedOnboardingPlan assignedOnboardingPlan = onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId);
        boolean isDue = assignedOnboardingPlan.isDue();

        if (isDue) {
            onboardingPlanAssignmentNotification.notifyRelevantParties(
                    NotificationType.ONBOARDING_PLAN_OVERDUE,
                    assignedOnboardingPlan.getOnboardingPlanId(),
                    List.of(assignedOnboardingPlan.getAssignerId(), assignedOnboardingPlan.getAssigneeId(), assignedOnboardingPlan.getPointOfContactId()));
        }

        return isDue;
    }

    @Override
    public void completeSteps(long assignedOnboardingPlanId, List<String> stepNames, String taskName) {
        AssignedOnboardingPlan assignedOnboardingPlan = onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId);
        assignedOnboardingPlan.completeSteps(stepNames, taskName);

        if (assignedOnboardingPlan.isPlanCompleted()) {
            onboardingPlanAssignmentNotification.notifyRelevantParties(
                    NotificationType.ONBOARDING_PLAN_COMPLETED,
                    assignedOnboardingPlan.getOnboardingPlanId(),
                    List.of(assignedOnboardingPlan.getAssignerId(), assignedOnboardingPlan.getAssigneeId(), assignedOnboardingPlan.getPointOfContactId()));
        }

        onboardingPlanAssignmentRepository.save(assignedOnboardingPlan);
    }
}
