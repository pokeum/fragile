package non_official.pokeum.fragile_codegen.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class SerialName(val value: String)