package com.modern.app.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;
import com.modern.app.domain.models.onboarding.tracker.AssignedTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@Document("onboarding-plan-assignment")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignedOnboardingPlanDto {

    @Id
    @JsonProperty("_id")
    private String id;

    private long assigneeId;
    private String onboardingPlanId;

    private boolean isPlanCompleted;

    private long pointOfContactId;

    private long assignerId;

    private List<AssignedTask> assignedTasks;

    private LocalDate dueDate;

    public AssignedOnboardingPlanDto(String id, long assigneeId, String onboardingPlanId, boolean isPlanCompleted, long pointOfContactId, long assignerId, List<AssignedTask> assignedTasks, LocalDate dueDate) {
        this.id = id;
        this.assigneeId = assigneeId;
        this.onboardingPlanId = onboardingPlanId;
        this.isPlanCompleted = isPlanCompleted;
        this.pointOfContactId = pointOfContactId;
        this.assignerId = assignerId;
        this.assignedTasks = assignedTasks;
        this.dueDate = dueDate;
    }

    public static AssignedOnboardingPlanDto from(AssignedOnboardingPlan assignedOnboardingPlan) {
        return new AssignedOnboardingPlanDto(
                assignedOnboardingPlan.getId(),
                assignedOnboardingPlan.getAssigneeId(),
                assignedOnboardingPlan.getOnboardingPlanId(),
                assignedOnboardingPlan.isPlanCompleted(),
                assignedOnboardingPlan.getPointOfContactId(),
                assignedOnboardingPlan.getAssignerId(),
                assignedOnboardingPlan.getAssignedTasks(),
                assignedOnboardingPlan.getDueDate()
        );
    }

    public AssignedOnboardingPlan toModel() {
        return new AssignedOnboardingPlan(id, assigneeId, onboardingPlanId, isPlanCompleted, pointOfContactId, assignerId, assignedTasks, dueDate);
    }

    public String getId() {
        return id.toString();
    }
}

