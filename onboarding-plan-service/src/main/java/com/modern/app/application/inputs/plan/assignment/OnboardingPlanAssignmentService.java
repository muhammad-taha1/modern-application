package com.modern.app.application.inputs.plan.assignment;

import com.modern.app.application.outputs.NotificationType;
import com.modern.app.application.outputs.OnboardingPlanAssignmentNotification;
import com.modern.app.application.outputs.OnboardingPlanAssignmentRepository;
import com.modern.app.application.outputs.OnboardingPlanRepository;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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
    public boolean isDue(String assignedOnboardingPlanId) throws OnboardingPlanException {
        AssignedOnboardingPlan assignedOnboardingPlan = onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId);
        return assignedOnboardingPlan.isDue();
    }

    @Override
    public void completeSteps(String assignedOnboardingPlanId, List<String> stepNames, String taskName) throws OnboardingPlanException {
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

    @Override
    public AssignedOnboardingPlan getAssignedOnboardingPlanById(String assignedOnboardingPlanId) throws OnboardingPlanException {
        return onboardingPlanAssignmentRepository.getById(assignedOnboardingPlanId);
    }
}
