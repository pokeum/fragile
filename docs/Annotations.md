# Annotations & Annotation Processor

## Table of content

- [Annotations](#annotations)
  - [What Are Annotations?](#what-are-annotations)
  - [Declaring Annotation Type](#declaring-annotation-type)
- [Annotation Processor](#annotation-processor)

## <a id="annotations"> Annotations

### <a id="what-are-annotations"> What Are Annotations?
- Annotations are human readable.
- Annotations are `compiler readable`.
- Annotations are also `available at runtime` so that a program can read and use it for any purpose it wants.

### <a id="declaring-annotation-type"> Declaring Annotation Type

- **Java**
  ```java
  @Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PACKAGE})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Version {
      int major() default 0;
      int minor() default 0;
      int patch() default 0;
  }
  ```
- **Kotlin**
  ```kotlin
  @Retention(AnnotationRetention.RUNTIME)
  @Target(AnnotationTarget.TYPE, AnnotationTarget.CONSTRUCTOR,
      /* METHOD */
      AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
      /* PACKAGE */
      AnnotationTarget.FILE)
  annotation class Version(
      val major: Int = 0,
      val minor: Int = 0,
      val patch: Int = 0
  )
  ```
  
#### The `Retention` Annotation Type

| Retention | Description |
| --- | --- |
| SOURCE | Source code(= `compile time`) only |
| CLASS | Class file only (the default) |
| RUNTIME | Class file and `runtime` |


## <a id="annotation-processor"> Annotation Processor