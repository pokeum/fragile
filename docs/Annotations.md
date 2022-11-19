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
  @Retention(RetentionPolicy.<retention-policy>)
  @Target(ElementType.<element-type>)
  [modifiers] @interface <annotation-type-name> {
      <data-type> <element-name>() default <default-value>;
  }
  ```
- **Kotlin**
  ```kotlin
  @Retention(AnnotationRetention.<annotation-retention>)
  @Target(AnnotationTarget.<annotation-target>)
  [modifiers] annotation class <annotation-type-name>(
      val <element-name>: <data-type> = <default-value>,
  )
  ```
  
#### The `Retention` Annotation Type

| Retention | Description |
| --- | --- |
| SOURCE | Source code only |
| CLASS | Class file only (the default) |
| RUNTIME | Class file and `runtime` |


## <a id="annotation-processor"> Annotation Processor