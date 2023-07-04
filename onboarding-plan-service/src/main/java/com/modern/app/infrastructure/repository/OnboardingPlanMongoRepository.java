package com.modern.app.infrastructure.repository;

import com.modern.app.infrastructure.dto.OnboardingPlanDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingPlanMongoRepository extends MongoRepository<OnboardingPlanDto, String> {

}
