package kr.pokeum.fragile.deserialization.kotlin

import kr.pokeum.fragile.Fragile
import kr.pokeum.fragile.element.JsonArray
import org.junit.Test

inline fun <reified T : Any> JsonArray.toList(): List<T> {
    val result = mutableListOf<T>()
    when (T::class) {
        Boolean::class -> { for (i in 0 until size) { result.add(get(i).asBoolean() as T) } }
        Byte::class -> { for (i in 0 until size) { result.add(get(i).asNumber()?.toByte() as T) } }
        Short::class -> { for (i in 0 until size) { result.add(get(i).asNumber()?.toShort() as T) } }
        Int::class -> { for (i in 0 until size) { result.add(get(i).asNumber()?.toInt() as T) } }
        Long::class -> { for (i in 0 until size) { result.add(get(i).asNumber()?.toLong() as T) } }
        Float::class -> { for (i in 0 until size) { result.add(get(i).asNumber()?.toFloat() as T) } }
        Double::class -> { for (i in 0 until size) { result.add(get(i).asNumber()?.toDouble() as T) } }
        String::class -> { for (i in 0 until size) { result.add(get(i).asString() as T) } }
        else -> { }
    }
    return result
}

class JsonArrayExtensionTest {

    @Test
    fun test() {
        val fragile = Fragile()
        val jsonArray = fragile.fromJson("[1, 2, 3, 4, 5]").asJsonArray()
        val list: List<Int> = jsonArray?.toList()!!
//        println(jsonArray?.toList<Int>())
        println(list)
    }
}

