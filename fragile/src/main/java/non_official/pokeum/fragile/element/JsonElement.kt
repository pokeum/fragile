package non_official.pokeum.fragile.element

interface JsonElement {

    fun isJsonObject(): Boolean = this is JsonObject
    fun isJsonArray(): Boolean = this is JsonArray
    fun isJsonNull(): Boolean = this is JsonNull

    fun asJsonObject(): JsonObject {
        if (isJsonObject()) { return this as JsonObject }
        throw IllegalStateException("Not a Json Object: $this")
    }

    fun asJsonArray(): JsonArray {
        if (isJsonArray()) { return this as JsonArray }
        throw IllegalStateException("Not a Json Array: $this")
    }

    fun asJsonNull(): JsonNull {
        if (isJsonNull()) { return this as JsonNull }
        throw IllegalStateException("Not a Json Null: $this")
    }

    /* As JsonPrimitive */
    fun asBoolean(): Boolean { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun asNumber(): Number { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun asString(): String { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }

    fun setProperty(propertyName: String, value: Any?) { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun createObject(propertyName: String): JsonElement { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun createArray(propertyName: String): JsonElement { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
}