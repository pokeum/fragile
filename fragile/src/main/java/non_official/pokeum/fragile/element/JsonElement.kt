package non_official.pokeum.fragile.element

interface JsonElement {
    val value : Any?

    fun setProperty(propertyName: String, value: Any?) {
        throw UnsupportedOperationException(this.javaClass.kotlin.simpleName)
    }

    fun createObject(propertyName: String): JsonObject {
        throw UnsupportedOperationException(this.javaClass.kotlin.simpleName)
    }

    fun createArray(propertyName: String): JsonArray {
        throw UnsupportedOperationException(this.javaClass.kotlin.simpleName)
    }
}