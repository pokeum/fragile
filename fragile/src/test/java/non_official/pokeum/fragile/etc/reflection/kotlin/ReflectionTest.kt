package non_official.pokeum.fragile.etc.reflection.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.reflect.full.memberProperties
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

class Person(val name: String, val age: Int)

fun foo(x: Int) = println(x)
fun sum(x: Int, y: Int) = x + y

class ReflectionTest {

    @Test
    fun kClassTest() {
        val kClass = Person::class
        assertEquals("Person", kClass.simpleName)

        kClass.memberProperties.forEach {
            println(it.name)
        }
    }

    @Test
    fun `kCallableTest (Foo)`() {
        val kFunction: KFunction1<Int, Unit> = ::foo
        kFunction.call(42)
    }

    @Test
    fun `kCallableTest (Sum)`() {
        val kFunction: KFunction2<Int, Int, Int> = ::sum
        assertEquals(3, kFunction.invoke(1, 2))
    }
}