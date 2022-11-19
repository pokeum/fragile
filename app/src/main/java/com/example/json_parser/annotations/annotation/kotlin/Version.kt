package com.example.json_parser.annotations.annotation.kotlin

@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.CONSTRUCTOR,
    /* METHOD */
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    /* PACKAGE */
    AnnotationTarget.FILE
 )
annotation class Version(
    val major: Int = 0,
    val minor: Int = 0,
    val patch: Int = 0
)