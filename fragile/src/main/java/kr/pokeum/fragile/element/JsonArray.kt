package kr.pokeum.fragile.element

import kr.pokeum.fragile.throwable.FragileException

class JsonArray: JsonElement() {
    override val value: JsonArray
        get() = this

    private val members = mutableListOf<JsonElement>()

    val size: Int
        get() = members.size

    operator fun get(index: Int): JsonElement { return members[index] }

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