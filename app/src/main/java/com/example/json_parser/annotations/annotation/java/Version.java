package com.example.json_parser.annotations.annotation.java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
    /**
     * Abstract methods in an annotation type are known as its elements.
     */
    int major() default 0;
    int minor() default 0;
    int patch() default 0;
}
