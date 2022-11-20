package non_official.pokeum.fragile.serialization

import non_official.pokeum.fragile.annotation.SerializedName
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

internal fun serialize(obj: Any?): String = if (obj == null) "null" else buildString { serializeObject(obj) }

private fun StringBuilder.serializeObject(obj: Any) {
    obj.javaClass.kotlin.memberProperties
        .joinToStringBuilder(this, prefix = "{", postfix = "}") {
            serializeProperty(it, obj)
        }
}

private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
    val key = prop.findAnnotation<SerializedName>()?.value ?: prop.name
    serializeString(key)
    append(": ")

    val value = prop.get(obj)
    serializePropertyValue(value)
}

internal fun <T> Iterable<T>.joinToStringBuilder(stringBuilder: StringBuilder,
                                        separator: CharSequence = ", ",
                                        prefix: CharSequence = "",
                                        postfix: CharSequence = "",
                                        limit: Int = -1,
                                        truncated: CharSequence = "...",
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
        null -> append("null")
        is String -> serializeString(value)
        is Number, is Boolean -> append(value.toString())
        is List<*> -> serializeList(value)
        is Map<*, *> -> serializeMap(value)
        else -> serializeObject(value)
    }
}

private fun StringBuilder.serializeList(data: List<Any?>) {
    data.joinToStringBuilder(this, prefix = "[", postfix = "]") {
        serializePropertyValue(it)
    }
}

private fun StringBuilder.serializeMap(data: Map<*, *>) {
    data.toList().joinToStringBuilder(this, prefix = "{", postfix = "}") { entry ->
        val (key, value) = entry
        serializeString(key.toString())
        append(": ")
        serializePropertyValue(value)
    }
}

private fun StringBuilder.serializeString(s: String) {
    append('\"')
    s.forEach { append(it.escape()) }
    append('\"')
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