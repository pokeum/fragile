package non_official.pokeum.fragile.serialization

import non_official.pokeum.fragile.annotation.SerializedName
import kotlin.reflect.KProperty1
import kotlin.reflect.full.IllegalCallableAccessException
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

internal class Serializer(
    private val serializeNulls: Boolean,
    private val escapeHtml: Boolean,
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
        append(KEY_SEPARATOR)

        val value = try {
            prop.get(obj)
        } catch (e: IllegalCallableAccessException) {
            prop.let {
                // access to private field and get value
                it.isAccessible = true
                val value = it.get(obj)

                it.isAccessible = false     // reset accessibility
                value
            }
        }
        serializePropertyValue(value)
    }

    fun <T> Iterable<T>.joinToStringBuilder(stringBuilder: StringBuilder,
                                            separator: CharSequence = VALUE_SEPARATOR,
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
            append(KEY_SEPARATOR)
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

    private fun Char.escape(): Any {
        val replacements = if (escapeHtml) HTML_SAFE_REPLACEMENT_CHARS else REPLACEMENT_CHARS
        if (replacements.containsKey(this)) {
            return replacements[this]!!
        }
        return this
    }

    companion object {
        const val DOUBLE_QUOTE = "\""

        const val SQUARE_BRACKET_BEGIN = "["
        const val SQUARE_BRACKET_END = "]"

        const val CURLY_BRACKET_BEGIN = "{"
        const val CURLY_BRACKET_END = "}"

        const val VALUE_SEPARATOR = ","
        const val KEY_SEPARATOR = ":"
        const val TRUNCATED = "..."
        const val NULL = "null"

        val REPLACEMENT_CHARS = mapOf(
            '"' to "\\\"",
            '\\' to "\\\\",
            '\t' to "\\t",
            '\b' to "\\b",
            '\n' to "\\n",
            '\r' to "\\r",
            '\u000C' to "\\f"
        )
        val HTML_SAFE_REPLACEMENT_CHARS = REPLACEMENT_CHARS.toMutableMap().apply {
            put('<', "\\u003c")
            put('>', "\\u003e")
            put('&', "\\u0026")
            put('=', "\\u003d")
            put('\'', "\\u0027")
        }
    }
}