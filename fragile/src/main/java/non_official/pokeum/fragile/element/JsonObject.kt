package non_official.pokeum.fragile.element

import non_official.pokeum.fragile.throwable.FragileException

class JsonObject: JsonElement {
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

    override fun createObject(propertyName: String): JsonElement {
        members[propertyName] = JsonObject()
        return members[propertyName]!!
    }

    override fun createArray(propertyName: String): JsonElement {
        members[propertyName] = JsonArray()
        return members[propertyName]!!
    }

    /**
     * Returns the value mapped by name, or throws if no such mapping exists.
     * @throws FragileException if no such mapping exists.
     * @throws NullPointerException if parameter is null. (java only)
     */
    fun get(key: String): Any? {
        val jsonElement = members[key] ?: throw FragileException("JsonObject[$key] not found.")
        return jsonElement.value
    }

    /**
     * Returns the value mapped by name, or null if no such mapping exists.
     */
    fun opt(key: String): Any? { return members[key]?.value }
}