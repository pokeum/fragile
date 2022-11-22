package non_official.pokeum.fragile.element

interface JsonElement {

    fun isJsonObject(): Boolean = this is JsonObject
    fun isJsonArray(): Boolean = this is JsonArray
    fun isJsonPrimitive(): Boolean = this is JsonPrimitive
    fun isJsonNull(): Boolean = this is JsonNull

    fun getAsJsonObject(): JsonObject {
        if (isJsonObject()) { return this as JsonObject }
        throw IllegalStateException("Not a Json Object: $this")
    }

    fun getAsJsonArray(): JsonArray {
        if (isJsonArray()) { return this as JsonArray }
        throw IllegalStateException("Not a Json Array: $this")
    }

    fun getAsJsonPrimitive(): JsonPrimitive {
        if (isJsonPrimitive()) { return this as JsonPrimitive }
        throw IllegalStateException("Not a Json Primitive: $this")
    }

    fun getAsJsonNull(): JsonNull {
        if (isJsonNull()) { return this as JsonNull }
        throw IllegalStateException("Not a Json Null: $this")
    }

    fun getAsBoolean(): Boolean { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun getAsNumber(): Number { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun getAsString(): String { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun getAsCharacter(): Char { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
}