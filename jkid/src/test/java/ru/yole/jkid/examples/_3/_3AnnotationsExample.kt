package ru.yole.jkid.examples._3

import org.junit.Test
import ru.yole.jkid.JsonExclude
import ru.yole.jkid.JsonName
import ru.yole.jkid.examples.testJsonSerializer

data class Person(
        @JsonName(name = "first_name") val firstName: String,
        @JsonExclude val age: Int? = null
)

class AnnotationsTest {
    @Test fun test() = testJsonSerializer(
            value = Person("Alice"),
            json = """{"first_name": "Alice"}"""
    )
}