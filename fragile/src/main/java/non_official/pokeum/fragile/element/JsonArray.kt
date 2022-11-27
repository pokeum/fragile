package non_official.pokeum.fragile.element

import non_official.pokeum.fragile.throwable.FragileException

class JsonArray: JsonElement {
    override val value: JsonArray
        get() = this

    private val members = mutableListOf<JsonElement>()

    override fun setProperty(propertyName: String, value: Any?) {
        when(value) {
            null -> members.add(JsonNull())
            is String -> members.add(JsonPrimitive(value))
            is Number -> members.add(JsonPrimitive(value))
            is Boolean -> members.add(JsonPrimitive(value))
            else -> throw FragileException("Type mismatch for property $propertyName: " +
                    "expected <String|Number|Boolean or null>, found ${value.javaClass.kotlin}")
        }
    }

    override fun createObject(propertyName: String): JsonObject {
        val jsonObject = JsonObject()
        members.add(jsonObject)
        return jsonObject
    }

    override fun createArray(propertyName: String): JsonArray {
        val jsonArray = JsonArray()
        members.add(jsonArray)
        return jsonArray
    }
}