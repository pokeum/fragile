package non_official.pokeum.fragile.etc.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class Lists {

    @Test
    fun `Generate a List of consecutive integers - Using List constructor`() {
        val n = 5
        val list = List(n) { it + 1 }
        assertEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun `Generate a List of consecutive integers - Using Kotlin Ranges 1`() {
        val n = 5
        val list = (1 .. n).toList()
        assertEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun `Generate a List of consecutive integers - Using Kotlin Ranges 2`() {
        val n = 5
        val list = (0 until n).map { it + 1 }
        assertEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun `List to Map`() {
        val map = (0 .. 0x1f).toList().map {
            it.toChar() to String.format("\\u%04x", it)
        }.toMap()
        println(map)
    }
}