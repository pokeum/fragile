package kr.pokeum.fragile.serialization

import kr.pokeum.fragile.annotation.SerializedName
import java.util.regex.Pattern
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
        is Char, is CharSequence -> buildString { serializeString(obj.toString()) }
        is Number, is Boolean -> obj.toString()
        is Array<*> -> buildString { serializeList(obj.toList()) }
        is Set<*> -> buildString { serializeList(obj.toList()) }
        is List<*> -> buildString { serializeList(obj) }
        is Map<*, *> -> buildString { serializeMap(obj) }
        else -> buildString { serializeObject(obj) }
    }

    private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
        val key = prop.findAnnotation<SerializedName>()?.value ?: prop.name
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

        if (serializeNulls || value != null) {
            serializeString(key)
            append(KEY_SEPARATOR)
            serializePropertyValue(value)
        }
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
            if (serializeNulls || value != null) {
                serializeString(key.toString())
                append(KEY_SEPARATOR)
                serializePropertyValue(value)
            }
        }
        if (!serializeNulls) { jsonObjectSyntaxCheck() }
    }

    private fun StringBuilder.serializeObject(obj: Any) {
        obj.javaClass.kotlin.memberProperties
            .joinToStringBuilder(this,
                prefix = CURLY_BRACKET_BEGIN, postfix = CURLY_BRACKET_END) {
                serializeProperty(it, obj)
            }
        if (!serializeNulls) { jsonObjectSyntaxCheck() }
    }

    private fun StringBuilder.serializeString(s: String) {
        append(DOUBLE_QUOTE)
        s.forEach { append(it.escape()) }
        append(DOUBLE_QUOTE)
    }

    /**
     * must run Json Object syntax check when Serialize Nulls is disabled.
     *
     * from: {,,"key1":"value1",,,"key2":"value2",,,}
     * to: {"key1":"value1","key2":"value2"}
     */
    private fun StringBuilder.jsonObjectSyntaxCheck() {
        val pBegin = Pattern.compile("(\\$CURLY_BRACKET_BEGIN)(\\$VALUE_SEPARATOR)+")
        val pSeparator = Pattern.compile("(\\$VALUE_SEPARATOR)+")
        val pEnd = Pattern.compile("(\\$VALUE_SEPARATOR)+(\\$CURLY_BRACKET_END)")

        replace(0, length, pBegin.matcher(this).replaceAll(CURLY_BRACKET_BEGIN))
        replace(0, length, pSeparator.matcher(this).replaceAll(VALUE_SEPARATOR))
        replace(0, length, pEnd.matcher(this).replaceAll(CURLY_BRACKET_END))
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

        val REPLACEMENT_CHARS = (0..0x1f).toList().associate {
            it.toChar() to String.format("\\u%04x", it)     // 4 hexadecimal digits ("\u0000" to "\u001f")
        }.toMutableMap().apply {
            put('"', "\\\"")        // quotation mark
            put('\\', "\\\\")       // reverse solidus
            put('\b', "\\b")        // backspace
            put('\n', "\\n")        // newline
            put('\r', "\\r")        // carriage return
            put('\t', "\\t")        // horizontal tab
            put('\u000c', "\\f")    // formfeed
        }
        val HTML_SAFE_REPLACEMENT_CHARS = REPLACEMENT_CHARS.toMutableMap().apply {
            put('<', "\\u003c")
            put('>', "\\u003e")
            put('&', "\\u0026")
            put('=', "\\u003d")
            put('\'', "\\u0027")
        }
    }
}