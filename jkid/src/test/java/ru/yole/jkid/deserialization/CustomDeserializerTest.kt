package ru.yole.jkid.deserialization

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.yole.jkid.serialization.serialize

class CustomDeserializerTest {

    @Test
    fun withNull() {
        val subject = NullableData(null)
        val jsonString = serialize(subject)
        val deserializeSubject = deserialize<NullableData>(jsonString)
        assertEquals(null, deserializeSubject.value)
    }

    @Test
    fun withChar() {
        val subject = CharacterData('A')
        val jsonString = serialize(subject)
        val deserializeSubject = deserialize<CharacterData>(jsonString)
        //println(deserializeSubject.value)
        assertEquals('A', deserializeSubject.value)
    }

    @Test
    fun withString() {
        val subject = StringData("JKID")
        val jsonString = serialize(subject)
        val deserializeSubject = deserialize<StringData>(jsonString)
        assertEquals("JKID", deserializeSubject.value)
    }

    @Test
    fun withNumber() {
        val subject = NumberData(123.456)
        val jsonString = serialize(subject)
        val deserializeSubject = deserialize<NumberData>(jsonString)
        println(deserializeSubject.value)
        //assertEquals(123.456, deserializeSubject.value)
    }
}

data class NullableData(val value: Any?)
data class CharacterData(val value: Char)
data class StringData(val value: String)
data class NumberData(val value: Number)