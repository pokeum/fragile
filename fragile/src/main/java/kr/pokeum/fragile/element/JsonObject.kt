package kr.pokeum.fragile.element

import kr.pokeum.fragile.throwable.FragileException

class JsonObject: JsonElement() {
    override val value: JsonObject
        get() = this

    private val members = mutableMapOf<String, JsonElement>()

    override fun setProperty(propertyName: String, value: Any?) {
        members[propertyName] = when(value) {
            null -> JsonNull()
            is String -> JsonPrimitive(value)
            is Number -> JsonPrimitive(value)
            is Boolean -> JsonPrimitive(value)
            else -> throw FragileException("Type mismatch for property $propertyName: " +
                    "expected <String|Number|Boolean or null>, found ${value.javaClass.kotlin}")
        }
    }

    override fun createObject(propertyName: String): JsonObject {
        val jsonObject = JsonObject()
        members[propertyName] = jsonObject
        return jsonObject
    }

    override fun createArray(propertyName: String): JsonArray {
        val jsonArray = JsonArray()
        members[propertyName] = jsonArray
        return jsonArray
    }

    /**
     * Returns the "JsonElement" mapped by name, or "JsonNull" if no such mapping exists.
     */
    private fun getJsonElement(key: String): JsonElement { return members[key] ?: JsonNull() }

    /**
     * Returns the value mapped by name, or null if no such mapping exists.
     */
    fun get(key: String): Any? { return getJsonElement(key).value }

    fun getJsonObject(key: String): JsonObject? {
        return when(val jsonElement = getJsonElement(key)) {
            is JsonObject -> jsonElement
            is JsonNull -> null
            else -> throw FragileException("JsonObject[$key] is not a JsonObject.")
        }
    }

    fun getJsonArray(key: String): JsonArray? {
        return when(val jsonElement = getJsonElement(key)) {
            is JsonArray -> jsonElement
            is JsonNull -> null
            else -> throw FragileException("JsonObject[$key] is not a JsonArray.")
        }
    }
}