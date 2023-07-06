package com.modern.app.infrastructure.repository;

import com.modern.app.infrastructure.dto.AssignedOnboardingPlanDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingPlanAssignmentMongoRepository extends MongoRepository<AssignedOnboardingPlanDto, String> {
}
