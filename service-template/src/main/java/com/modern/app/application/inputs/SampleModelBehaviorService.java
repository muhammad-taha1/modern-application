package com.modern.app.application.inputs;


import com.modern.app.application.outputs.SampleModelRepository;
import com.modern.app.domain.models.SampleModel;
import org.springframework.stereotype.Service;

@Service
public class SampleModelBehaviorService implements SampleModelBehavior {

    SampleModelRepository sampleModelRepository;

    public SampleModelBehaviorService(SampleModelRepository sampleModelRepository) {
        this.sampleModelRepository = sampleModelRepository;
    }

    @Override
    public String sayHello() {
        SampleModel sampleModel = sampleModelRepository.getSampleModel();
        sampleModel.updateToHelloWorld();
        return sampleModel.getProperty();
    }

    @Override
    public void saveCustomMessage(String message) {
        SampleModel sampleModel = new SampleModel(message);
        sampleModelRepository.saveSampleModel(sampleModel);
    }

    @Override
    public String getCurrentMessage() {
        SampleModel sampleModel = sampleModelRepository.getSampleModel();
        return sampleModel.getProperty();
    }
}
