package com.modern.app.infrastructure.dto;

import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.domain.models.onboarding.plan.Task;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("onboarding-plan")
@Data
public class OnboardingPlanDto {

    @Id
    private String id;
    private String name;
    private long createdBy;
    private List<Task> tasks;

    public OnboardingPlanDto(String id, String name, long createdBy, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.tasks = tasks;
    }

    public static OnboardingPlanDto from(OnboardingPlan onboardingPlan) {
        return new OnboardingPlanDto(onboardingPlan.id(), onboardingPlan.name(), onboardingPlan.createdBy(), onboardingPlan.tasks());
    }

    public OnboardingPlan toModel() {
        return new OnboardingPlan(id, name, createdBy, tasks);
    }
}
