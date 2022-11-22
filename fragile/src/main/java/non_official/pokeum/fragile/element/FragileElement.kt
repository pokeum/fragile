package non_official.pokeum.fragile.element

interface FragileElement {

    fun isFragileObject(): Boolean = this is FragileObject
    fun isFragileArray(): Boolean = this is FragileArray
    fun isFragilePrimitive(): Boolean = this is FragilePrimitive
    fun isFragileNull(): Boolean = this is FragileNull

    fun getAsFragileObject(): FragileObject {
        if (isFragileObject()) { return this as FragileObject }
        throw IllegalStateException("Not a Fragile Object: $this")
    }

    fun getAsFragileArray(): FragileArray {
        if (isFragileArray()) { return this as FragileArray }
        throw IllegalStateException("Not a Fragile Array: $this")
    }

    fun getAsFragilePrimitive(): FragilePrimitive {
        if (isFragilePrimitive()) { return this as FragilePrimitive }
        throw IllegalStateException("Not a Fragile Primitive: $this")
    }

    fun getAsFragileNull(): FragileNull {
        if (isFragileNull()) { return this as FragileNull }
        throw IllegalStateException("Not a Fragile Null: $this")
    }

    fun getAsBoolean(): Boolean { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun getAsNumber(): Number { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun getAsString(): String { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
    fun getAsCharacter(): Char { throw UnsupportedOperationException(this.javaClass.kotlin.simpleName) }
}