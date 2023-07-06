package com.modern.app.infrastructure.dto;

import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.plan.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("onboarding-plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingPlanDto {

    @Id
    private String id;

    private String name;
    private long createdBy;
    private List<Task> tasks;

    public OnboardingPlanDto(String name, long createdBy, List<Task> tasks) {
        this.name = name;
        this.createdBy = createdBy;
        this.tasks = tasks;
    }

    public static OnboardingPlanDto from(OnboardingPlan onboardingPlan) {
        if (onboardingPlan.id().isEmpty()) {
            return new OnboardingPlanDto(onboardingPlan.name(), onboardingPlan.createdBy(), onboardingPlan.tasks());
        }
        return new OnboardingPlanDto(onboardingPlan.id(), onboardingPlan.name(), onboardingPlan.createdBy(), onboardingPlan.tasks());
    }

    public OnboardingPlan toModel() {
        return new OnboardingPlan(id, name, createdBy, tasks);
    }
}
