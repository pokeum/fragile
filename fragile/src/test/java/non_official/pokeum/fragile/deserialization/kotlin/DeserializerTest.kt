package non_official.pokeum.fragile.deserialization.kotlin

import non_official.pokeum.fragile.Fragile
import org.junit.Test

class DeserializerTest {

    @Test
    fun testCar() {
        val json = """
            {
              "name": "My Car\u0027s Name",
              "speed": 15,
              "gear": 1,
              "drivetrain": 4,
              "direction": "North",
              "color": "Red",
              "fuel": "Gas",
              "price": 21795.0
            }
        """.trimIndent()
        val jsonObject = Fragile().fromJson(json).getAsJsonObject()

        println(jsonObject.get("name").getAsJsonPrimitive().getAsString())
    }
}