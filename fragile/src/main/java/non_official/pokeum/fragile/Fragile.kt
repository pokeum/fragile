package non_official.pokeum.fragile

import non_official.pokeum.fragile.deserialization.Parser
import non_official.pokeum.fragile.element.JsonElement
import non_official.pokeum.fragile.element.JsonObject
import non_official.pokeum.fragile.serialization.Serializer
import java.io.StringReader

class Fragile() {
    internal var serializeNulls: Boolean = DEFAULT_SERIALIZE_NULLS
    internal var escapeHtml: Boolean = DEFAULT_ESCAPE_HTML

    internal constructor(
        serializeNulls: Boolean,
        escapeHtml: Boolean,
    ): this() {
        this.serializeNulls = serializeNulls
        this.escapeHtml = escapeHtml
    }

    /**
     * Serialization
     */
    fun toJson(obj: Any?): String = Serializer(serializeNulls, escapeHtml).serialize(obj)

    /**
     * Deserialization
     */
    fun fromJson(json: String): JsonElement {
        val jsonObject = JsonObject()
        Parser(StringReader(json), jsonObject).parse()
        return jsonObject
    }

    companion object {
        const val DEFAULT_SERIALIZE_NULLS = true
        const val DEFAULT_ESCAPE_HTML = true
    }
}