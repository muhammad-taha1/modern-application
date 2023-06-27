package com.modern.app.infrastructure.repository;

import com.modern.app.application.outputs.SampleModelRepository;
import com.modern.app.domain.models.SampleModel;
import com.modern.app.infrastructure.dto.SampleModelDto;
import org.springframework.stereotype.Component;

@Component
public class SampleModelRepositoryImpl implements SampleModelRepository {

    private SampleModelMongoRepository sampleModelMongoRepository;

    public SampleModelRepositoryImpl(SampleModelMongoRepository sampleModelMongoRepository) {
        this.sampleModelMongoRepository = sampleModelMongoRepository;
    }

    @Override
    public SampleModel getSampleModel() {
        SampleModelDto sampleModelDto = sampleModelMongoRepository.findAll().iterator().next();
        return sampleModelDto.toModel();
    }

    @Override
    public void saveSampleModel(SampleModel sampleModel) {
        sampleModelMongoRepository.save(SampleModelDto.fromModel(sampleModel));
    }
}
