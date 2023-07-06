package com.modern.app.infrastructure.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AssignedOnboardingRequest {

    private long assigneeId;
    private long pointOfContactId;
    private String onboardingPlanId;
    private long assignerId;
    private LocalDate dueDate;

    public AssignedOnboardingRequest(long assigneeId, long pointOfContactId, String onboardingPlanId, long assignerId, LocalDate dueDate) {
        this.assigneeId = assigneeId;
        this.pointOfContactId = pointOfContactId;
        this.onboardingPlanId = onboardingPlanId;
        this.assignerId = assignerId;
        this.dueDate = dueDate;
    }
}
