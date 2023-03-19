package kr.pokeum.fragile.element

class JsonNull: JsonElement() {
    override val value: Any?
        get() = null
}