package kr.pokeum.fragile.deserialization.kotlin

import kr.pokeum.fragile.Fragile
import kr.pokeum.fragile.util.TestUtils
import org.junit.Test

class DeserializerTest {

    @Test
    fun testCar() {
        val jsonString = TestUtils.getStringFromFile("car.json")
        val jsonArray = Fragile().fromJson(jsonString).asJsonArray()

        if (jsonArray != null) {
            println(jsonArray[0].asJsonObject()?.get("name"))
        } else {
            println("FAILURE")
        }
    }

    @Test
    fun testCake() {
        val jsonString = TestUtils.getStringFromFile("cake.json")
        val jsonObject = Fragile().fromJson(jsonString)
    }
}