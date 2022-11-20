package non_official.pokeum.fragile

import non_official.pokeum.fragile.serialization.Serializer

class Fragile() {
    internal var serializeNulls: Boolean = DEFAULT_SERIALIZE_NULLS

    internal constructor(
        serializeNulls: Boolean
    ): this() {
        this.serializeNulls = serializeNulls
    }

    /**
     * Serialization
     */
    fun toJson(obj: Any?): String = Serializer(serializeNulls).serialize(obj)

    companion object {
        const val DEFAULT_SERIALIZE_NULLS = true
    }
}