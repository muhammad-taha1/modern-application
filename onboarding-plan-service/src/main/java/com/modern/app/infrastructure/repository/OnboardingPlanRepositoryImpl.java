package com.modern.app.infrastructure.repository;

import com.modern.app.application.outputs.OnboardingPlanRepository;
import com.modern.app.domain.exceptions.OnboardingPlanException;
import com.modern.app.domain.models.onboarding.plan.OnboardingPlan;
import com.modern.app.infrastructure.dto.OnboardingPlanDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OnboardingPlanRepositoryImpl implements OnboardingPlanRepository {

    private OnboardingPlanMongoRepository onboardingPlanMongoRepository;

    public OnboardingPlanRepositoryImpl(OnboardingPlanMongoRepository onboardingPlanMongoRepository) {
        this.onboardingPlanMongoRepository = onboardingPlanMongoRepository;
    }

    @Override
    public String save(OnboardingPlan onboardingPlan) {
        OnboardingPlanDto onboardingPlanDto = OnboardingPlanDto.from(onboardingPlan);
        return onboardingPlanMongoRepository.save(onboardingPlanDto).getId();
    }

    @Override
    public OnboardingPlan getById(String onboardingPlanId) throws OnboardingPlanException {
        Optional<OnboardingPlanDto> onboardingPlanOptional = onboardingPlanMongoRepository.findById(onboardingPlanId);
        if (onboardingPlanOptional.isEmpty())
            throw new OnboardingPlanException("Plan with id %s not found".formatted(onboardingPlanId));


        return onboardingPlanOptional.get().toModel();
    }

    @Override
    public void remove(String onboardingPlanId) {
        onboardingPlanMongoRepository.deleteById(onboardingPlanId);
    }
}
