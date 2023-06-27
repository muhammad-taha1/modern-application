package com.modern.app.infrastructure.dto;

import com.modern.app.domain.models.SampleModel;

public record SampleModelDto(Long id, String message) {

    public SampleModel toModel() {
        return new SampleModel(this.message);
    }

    public static SampleModelDto fromModel(SampleModel sampleModel) {
        return new SampleModelDto(sampleModel.getId(), sampleModel.getProperty());
    }
}
