package com.modern.app.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modern.app.application.outputs.OnboardingPlanAssignmentRepository;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.tracker.AssignedOnboardingPlan;
import com.modern.app.infrastructure.dto.AssignedOnboardingPlanDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;


@Repository
public class OnboardingPlanAssignmentRepositoryImpl implements OnboardingPlanAssignmentRepository {

    private OnboardingPlanAssignmentMongoRepository onboardingPlanAssignmentMongoRepository;

    private MongoTemplate mongoTemplate;

    public OnboardingPlanAssignmentRepositoryImpl(OnboardingPlanAssignmentMongoRepository onboardingPlanAssignmentMongoRepository, MongoTemplate mongoTemplate) {
        this.onboardingPlanAssignmentMongoRepository = onboardingPlanAssignmentMongoRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String save(AssignedOnboardingPlan assignedOnboardingPlan) {
        AssignedOnboardingPlanDto savedAssignmentPlan = onboardingPlanAssignmentMongoRepository.save(AssignedOnboardingPlanDto.from(assignedOnboardingPlan));
        return savedAssignmentPlan.getId();
    }

    @Override
    public AssignedOnboardingPlan getById(String onboardingPlanAssignmentId) throws OnboardingPlanException {
        Map testObj = mongoTemplate.findById(onboardingPlanAssignmentId, Map.class, "onboarding-plan-assignment");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        modelMapper.map(testObj, AssignedOnboardingPlanDto.class);

        Optional<AssignedOnboardingPlanDto> assignedOnboardingPlanDto = onboardingPlanAssignmentMongoRepository.findById(onboardingPlanAssignmentId);

        if (assignedOnboardingPlanDto.isEmpty()) {
            throw new OnboardingPlanException("assigned onboarding not found");
        }

        return assignedOnboardingPlanDto.get().toModel();
    }
}
