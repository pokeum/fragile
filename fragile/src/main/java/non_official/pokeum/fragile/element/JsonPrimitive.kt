package non_official.pokeum.fragile.element

class JsonPrimitive: JsonElement {
    private var value: Any

    constructor(value: Boolean) { this.value = value }
    constructor(value: Number) { this.value = value }
    constructor(value: String) { this.value = value }

    fun isBoolean(): Boolean = this.value is Boolean
    fun isNumber(): Boolean = this.value is Number
    fun isString(): Boolean = this.value is String

    override fun getAsBoolean(): Boolean {
        if (isBoolean()) { return this.value as Boolean }
        throw IllegalStateException("Not a Boolean: $this")
    }

    override fun getAsNumber(): Number {
        if (isNumber()) { return this.value as Number }
        throw IllegalStateException("Not a Number: $this")
    }

    override fun getAsString(): String {
        if (isString()) { return this.value as String }
        throw IllegalStateException("Not a String: $this")
    }
}