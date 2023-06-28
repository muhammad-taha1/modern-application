package com.modern.app.domain.models;

public class SampleModel {

    private String property;

    private Long id;

    public SampleModel(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public Long getId() {
        return id;
    }

    public void updateToHelloWorld() {
        this.property = "Hello world";
    }
}
