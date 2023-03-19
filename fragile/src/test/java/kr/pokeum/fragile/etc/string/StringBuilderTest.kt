package kr.pokeum.fragile.etc.string

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.regex.Matcher
import java.util.regex.Pattern

class StringBuilderTest {

    @Test
    fun `Use regex to replace patterns in StringBuilder`() {

        val stringBuilder = StringBuilder()
        stringBuilder.append("{,,\"key1\":\"value1\",,,\"key2\":\"value2\",,,}")

        val pattern1 = Pattern.compile("(\\$CURLY_BRACKET_BEGIN)(\\$VALUE_SEPARATOR)+")
        val pattern2 = Pattern.compile("(\\$VALUE_SEPARATOR)+")
        val pattern3 = Pattern.compile("(\\$VALUE_SEPARATOR)+(\\$CURLY_BRACKET_END)")

        val matcher1: Matcher = pattern1.matcher(stringBuilder)
        val matcher2: Matcher = pattern2.matcher(stringBuilder)
        val matcher3: Matcher = pattern3.matcher(stringBuilder)

        stringBuilder.replace(0, stringBuilder.length, matcher1.replaceAll(CURLY_BRACKET_BEGIN))
        stringBuilder.replace(0, stringBuilder.length, matcher2.replaceAll(VALUE_SEPARATOR))
        stringBuilder.replace(0, stringBuilder.length, matcher3.replaceAll(CURLY_BRACKET_END))

        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", stringBuilder.toString())
    }

    companion object {
        const val CURLY_BRACKET_BEGIN = "{"
        const val CURLY_BRACKET_END = "}"

        const val VALUE_SEPARATOR = ","
    }
}