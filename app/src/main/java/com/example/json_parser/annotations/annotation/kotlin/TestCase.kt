package com.example.json_parser.annotations.annotation.kotlin

import com.example.json_parser.annotations.exception.DefaultException
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(
    /* METHOD */
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class TestCase(
    val willThrow: KClass<out Throwable> = DefaultException::class
)
