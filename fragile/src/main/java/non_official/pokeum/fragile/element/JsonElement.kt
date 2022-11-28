package non_official.pokeum.fragile.element

interface JsonElement {
    val value : Any?

    fun asJsonObject(): JsonObject? {
        return when (this) {
            is JsonNull -> null
            is JsonObject -> this
            else -> throw IllegalStateException("Not a Json Object: ${this.javaClass.kotlin.simpleName}")
        }
    }

    fun asJsonArray(): JsonArray? {
        return when (this) {
            is JsonNull -> null
            is JsonArray -> this
            else -> throw IllegalStateException("Not a Json Array: ${this.javaClass.kotlin.simpleName}")
        }
    }

    fun asBoolean(): Boolean? {
        return if (this is JsonNull) {
            null
        } else if (this is JsonPrimitive && this.isBoolean()) {
            this.value as Boolean
        } else {
            throw IllegalStateException("Not a Boolean: ${this.javaClass.kotlin.simpleName}")
        }
    }

    fun asNumber(): Number? {
        return if (this is JsonNull) {
            null
        } else if (this is JsonPrimitive && this.isNumber()) {
            this.value as Number
        } else {
            throw IllegalStateException("Not a Number: ${this.javaClass.kotlin.simpleName}")
        }
    }

    fun asString(): String? {
        return if (this is JsonNull) {
            null
        } else if (this is JsonPrimitive && this.isString()) {
            this.value as String
        } else {
            throw IllegalStateException("Not a String: ${this.javaClass.kotlin.simpleName}")
        }
    }

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