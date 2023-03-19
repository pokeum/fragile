package kr.pokeum.fragile_codegen

import org.junit.Test
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class GenericTypeUtil {

    @Test
    fun main() {
        get<Int>()
        get<List<Int>>()
        get<Map<String, List<Int>>>()

        get<List<Array<String>?>?>()
    }

    inline fun <reified T : Any?> get() {
        generateReturnType(typeOf<T>())
    }

    fun generateReturnType(type: KType) {
        println(
            """
            ${"=".repeat(100)}
            Class $type
                - marked nullable: ${type.isMarkedNullable}
                - parameters ${type.arguments}
            """.trimIndent()
        )
        if (type.arguments.isNotEmpty()) { println("-".repeat(100)) }
        for (i in 0 until type.arguments.size) {
            println(
                """
                ${"\\"}Inner Class ${type.arguments[i].type}
                    ${"\\"}- marked nullable: ${type.arguments[i].type?.isMarkedNullable}
                """.trimIndent().replace("\\", "\t")
            )
        }
    }
}