package non_official.pokeum.fragile.serialization

import non_official.pokeum.fragile.annotation.SerializedName
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

internal class Serializer(
    private val serializeNulls: Boolean
) {
    fun serialize(obj: Any?): String = when(obj) {
        null -> NULL
        is Char, is CharSequence -> DOUBLE_QUOTE + obj.toString() + DOUBLE_QUOTE
        is Number, is Boolean -> obj.toString()
        is Array<*> -> buildString { serializeList(obj.toList()) }
        is Set<*> -> buildString { serializeList(obj.toList()) }
        is List<*> -> buildString { serializeList(obj) }
        is Map<*, *> -> buildString { serializeMap(obj) }
        else -> buildString { serializeObject(obj) }
    }

    private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
        val key = prop.findAnnotation<SerializedName>()?.value ?: prop.name
        serializeString(key)
        append(VALUE_SEPARATOR)

        val value = prop.get(obj)
        serializePropertyValue(value)
    }

    fun <T> Iterable<T>.joinToStringBuilder(stringBuilder: StringBuilder,
                                            separator: CharSequence = KEY_SEPARATOR,
                                            prefix: CharSequence = "",
                                            postfix: CharSequence = "",
                                            limit: Int = -1,
                                            truncated: CharSequence = TRUNCATED,
                                            callback: ((T) -> Unit)? = null
    ): StringBuilder {
        return joinTo(stringBuilder, separator, prefix, postfix, limit, truncated) {
            if (callback == null) return@joinTo it.toString()
            callback(it)
            ""
        }
    }

    private fun StringBuilder.serializePropertyValue(value: Any?) {
        when (value) {
            null -> append(NULL)
            is Char, is CharSequence -> serializeString(value.toString())
            is Number, is Boolean -> append(value.toString())
            is Array<*> -> serializeList(value.toList())
            is Set<*> -> serializeList(value.toList())
            is List<*> -> serializeList(value)
            is Map<*, *> -> serializeMap(value)
            else -> serializeObject(value)
        }
    }

    private fun StringBuilder.serializeList(data: List<Any?>) {
        data.joinToStringBuilder(this,
            prefix = SQUARE_BRACKET_BEGIN, postfix = SQUARE_BRACKET_END) {
            serializePropertyValue(it)
        }
    }

    private fun StringBuilder.serializeMap(data: Map<*, *>) {
        data.toList().joinToStringBuilder(this,
            prefix = CURLY_BRACKET_BEGIN, postfix = CURLY_BRACKET_END) { entry ->
            val (key, value) = entry
            serializeString(key.toString())
            append(VALUE_SEPARATOR)
            serializePropertyValue(value)
        }
    }

    private fun StringBuilder.serializeObject(obj: Any) {
        obj.javaClass.kotlin.memberProperties
            .joinToStringBuilder(this,
                prefix = CURLY_BRACKET_BEGIN, postfix = CURLY_BRACKET_END) {
                serializeProperty(it, obj)
            }
    }

    private fun StringBuilder.serializeString(s: String) {
        append(DOUBLE_QUOTE)
        s.forEach { append(it.escape()) }
        append(DOUBLE_QUOTE)
    }

    private fun Char.escape(): Any =
        when (this) {
            '\\' -> "\\\\"
            '\"' -> "\\\""
            '\b' -> "\\b"
            '\u000C' -> "\\f"
            '\n' -> "\\n"
            '\r' -> "\\r"
            '\t' -> "\\t"
            else -> this
        }

    companion object {
        const val DOUBLE_QUOTE = "\""

        const val SQUARE_BRACKET_BEGIN = "["
        const val SQUARE_BRACKET_END = "]"

        const val CURLY_BRACKET_BEGIN = "{"
        const val CURLY_BRACKET_END = "}"

        const val KEY_SEPARATOR = ", "
        const val VALUE_SEPARATOR = ": "
        const val TRUNCATED = "..."
        const val NULL = "null"
    }
}