package non_official.pokeum.fragile.etc.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class Collections {

    @Test
    fun joinToTest() {
        val stringBuilder = StringBuilder()
        val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        assertEquals("[1|2|3|4|5|...]",
            numbers.joinTo(stringBuilder,
                "|",
                "[",
                "]",
                5,
                "...").toString()
        )
    }
}