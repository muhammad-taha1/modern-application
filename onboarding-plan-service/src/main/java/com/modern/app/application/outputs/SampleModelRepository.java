package com.modern.app.application.outputs;


import com.modern.app.domain.models.SampleModel;

public interface SampleModelRepository {

    SampleModel getSampleModel();

    void saveSampleModel(SampleModel sampleModel);
}
