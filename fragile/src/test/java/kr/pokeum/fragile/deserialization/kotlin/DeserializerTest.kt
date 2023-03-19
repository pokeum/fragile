package kr.pokeum.fragile.deserialization.kotlin

import kr.pokeum.fragile.Fragile
import kr.pokeum.fragile.deserialization.kotlin.model.*
import kr.pokeum.fragile.util.TestUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class DeserializerTest {

    @Test
    fun testBook() {
        val jsonString = TestUtils.getStringFromFile("book.json")
        val jsonObject = Fragile().fromJson(jsonString).asJsonObject()

        val expected = Book(
            "The Catcher in the Rye",
            Author("J.D. Salinger", 1919, null),
            Publisher("Little, Brown and Company", 1951, Location("Boston", "MA"))
        )
        assertEquals(expected, jsonObject!!.toBook())
    }
}