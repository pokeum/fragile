package com.example.annotation_processor.java;

import com.example.annotations.annotation.java.Version;
import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@AutoService(Processor.class)
public class VersionProcessor extends AbstractProcessor {

    // A no-args constructor is required for an annotation processor
    public VersionProcessor() {
        System.out.println("VersionProcessor() called");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        System.out.println("VersionProcessor::process() called");

        // Process all annotations
        for (TypeElement currentAnnotation: annotations) {
            Name qualifiedName = currentAnnotation.getQualifiedName();
            // check if it is a Version annotation
            if (qualifiedName.contentEquals(Version.class.getCanonicalName())) {
                // Look at all elements that have Version annotations
                Set<? extends Element> annotationElement = roundEnvironment.getElementsAnnotatedWith(currentAnnotation);
                for (Element element: annotationElement) {
                    Version version = element.getAnnotation(Version.class);
                    int major = version.major();
                    int minor = version.minor();
                    int patch = version.patch();
                    if (major < 0 || minor < 0 || patch < 0) {
                        // Print the error message
                        String errorMessage = "Version cannot be negative. {" +
                                "major=" + major + ", minor=" + minor + "" + ", patch=" + patch + "}";
                        Messager messager = this.processingEnv.getMessager();
                        messager.printMessage(Kind.ERROR, errorMessage, element);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>() {{
            add(Version.class.getCanonicalName());
        }};
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}