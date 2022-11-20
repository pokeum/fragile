package com.example.annotation_processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({ "com.example.json_parser.annotations.annotation.java.Version",
        "com.example.json_parser.annotations.annotation.kotlin.Version" })
public class VersionProcessor extends AbstractProcessor {

    // A no-args constructor is required for an annotation processor
    public VersionProcessor() {
        System.out.println("VersionProcessor() called");
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}