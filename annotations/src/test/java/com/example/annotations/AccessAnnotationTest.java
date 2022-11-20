package com.example.annotations;

import com.example.annotations.annotation.java.Version;
import com.example.annotations.test.AccessAnnotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

public class AccessAnnotationTest {

    @Test
    public void main() {
        // Read annotations on the class declaration
        Class<AccessAnnotation> cls = AccessAnnotation.class;
        System.out.println("Annotation for class: " + cls.getName());
        printAnnotations(cls);

        // Read annotations on the package declaration (package-info.java)
        Package testPackage = cls.getPackage();
        System.out.println("Annotation for package: " + testPackage.getName());
        printAnnotations(testPackage);

        // Read annotations on the method declarations
        System.out.println("Method annotations:");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method: methods) {
            System.out.println("Annotations for method: " + method.getName());
            printAnnotations(method);
        }
    }

    private void printAnnotations(AnnotatedElement programElement) {
        Annotation[] annotations = programElement.getAnnotations();
        for (Annotation annotation: annotations) {
            System.out.println(annotation);
            if (annotation instanceof Version) {
                Version version = (Version) annotation;
                int major = version.major();
                int minor = version.minor();
                int patch = version.patch();
                System.out.println("Found Version annotation: {" +
                        "major=" + major +
                        ", minor=" + minor + "" +
                        ", patch=" + patch + "}");
            }
        }
        System.out.println();
    }
}
