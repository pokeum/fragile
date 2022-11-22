package non_official.pokeum.fragile.etc.string

import org.junit.Assert
import org.junit.Test

// https://www.w3schools.com/java/java_regex.asp
// https://www.baeldung.com/kotlin/regular-expressions
class RegularExpressions {

    private fun regexConstructor(): Regex {
        val regex1 = Regex("a[bc]+d?")
        val regex2 = "a[bc]+d?".toRegex()

        return regex1
    }

    @Test
    fun `Checking Partial or Total Matches`() {
        val regex = regexConstructor()
        Assert.assertTrue(regex.containsMatchIn("xabcdy"))
        Assert.assertTrue(regex.matches("abcd"))
        Assert.assertTrue(regex.matches("abcbc"))

        Assert.assertFalse(regex matches "xabcdy")
    }

    @Test
    fun `Extracting Matching Components`() {
        val regex = regexConstructor()
        val matchResult = regex.find("abcbabbd")
        Assert.assertEquals("abcb", matchResult?.value)
    }
}