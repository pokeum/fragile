package non_official.pokeum.fragile.element

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

    override fun asBoolean(): Boolean {
        if (isBoolean()) { return _value as Boolean }
        throw IllegalStateException("Not a Boolean: $this")
    }

    override fun asNumber(): Number {
        if (isNumber()) { return _value as Number }
        throw IllegalStateException("Not a Number: $this")
    }

    override fun asString(): String {
        if (isString()) { return _value as String }
        throw IllegalStateException("Not a String: $this")
    }
}