package non_official.pokeum.fragile.etc.reflection.kotlin

import org.junit.Test
import kotlin.reflect.full.IllegalCallableAccessException
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class Hello {
    private
    val integer = 10
    private
    val str = "World"
}

class AccessPrivateProperty {

    @Test
    fun main() {
        val obj = Hello()
        obj.javaClass.kotlin.memberProperties.forEach { prop ->
            println(prop.name)
            try {
                println(prop.get(obj))
            } catch (e: IllegalCallableAccessException) {
                prop.let {
                    it.isAccessible = true
                    println(it.get(obj))
                }
            }
        }
    }
}