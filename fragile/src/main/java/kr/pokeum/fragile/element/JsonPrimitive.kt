package kr.pokeum.fragile.element

class JsonPrimitive: JsonElement {
    private val _value: Any
    override val value: Any
        get() = _value

    constructor(value: Boolean) { _value = value }
    constructor(value: Number) { _value = value }
    constructor(value: String) { _value = value }

    fun isBoolean(): Boolean = _value is Boolean
    fun isNumber(): Boolean = _value is Number
    fun isString(): Boolean = _value is String
}