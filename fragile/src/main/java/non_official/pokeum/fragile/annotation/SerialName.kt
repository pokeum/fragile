package non_official.pokeum.fragile.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class SerialName(val value: String)
