package non_official.pokeum.fragile.element

class JsonObject(val value: String): JsonElement {
    val members = mutableMapOf<String, JsonElement>()

    init {

    }
}