package non_official.pokeum.fragile.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class SerializedName(val value: String)