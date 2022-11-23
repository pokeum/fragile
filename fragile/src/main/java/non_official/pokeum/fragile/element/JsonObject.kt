package non_official.pokeum.fragile.element

import non_official.pokeum.fragile.throwable.FragileException

class JsonObject: JsonElement {
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

    fun get(key: String): JsonElement { return members[key]!! } // TEST
}