package com.modern.app;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureEnforcerTest {


    @Test
    public void testThatHexagonalLayerIsRespected() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.modern.app");

        layeredArchitecture()
                .consideringAllDependencies()
                .layer("application").definedBy("..application..")
                .layer("domain").definedBy("..domain..")
                .layer("infrastructure").definedBy("..infrastructure..")
                .whereLayer("domain").mayOnlyBeAccessedByLayers("application", "infrastructure")
                .whereLayer("application").mayOnlyBeAccessedByLayers("infrastructure")
                .whereLayer("infrastructure").mayNotBeAccessedByAnyLayer()
                .check(classes);
    }
}

