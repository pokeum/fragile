package non_official.pokeum.fragile.deserialization.kotlin

import non_official.pokeum.fragile.Fragile
import non_official.pokeum.fragile.util.TestUtils
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class DeserializerTest {

    @Test
    @Ignore
    fun testCar() {
        val jsonString = TestUtils.getStringFromFile("car.json")
        val jsonObject = Fragile().fromJson(jsonString).asJsonObject()

        assertEquals("My Car's Name", jsonObject?.get("name"))

        assertEquals(15L, jsonObject?.get("speed"))      // Long
        assertEquals(15, jsonObject?.get("speed"))       // Integer (Wrong)

        assertEquals(21795.0, jsonObject?.get("price"))  // Double
    }

    @Test
    fun testCake() {
        val jsonString = TestUtils.getStringFromFile("cake.json")
        val jsonObject = Fragile().fromJson(jsonString)
    }
}