package com.example.json_parser.json_library.gson;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

/**
 * Primitive Type: byte, short, int, long, float, double, char, boolean.
 * (+ also test "String" object here)
 */
public class GsonPrimitiveTypeTest {

    Gson gson = new Gson();

    @Test
    public void withChar() {
        Character subject = 'a';

        assertEquals("\"a\"", gson.toJson(subject));
    }

    @Test
    public void withString() {
        String subject = "GSON";

        assertEquals("\"GSON\"", gson.toJson(subject));
    }

    @Test
    public void withByte() {
        Byte subject = 12;

        assertEquals("12", gson.toJson(subject));
    }

    @Test
    public void withShort() {
        Short subject = 123;

        assertEquals("123", gson.toJson(subject));
    }

    @Test
    public void withInteger() {
        Integer subject = 1234;

        assertEquals("1234", gson.toJson(subject));
    }

    @Test
    public void withLong() {
        Long subject = 12345L;

        assertEquals("12345", gson.toJson(subject));
    }

    @Test
    public void withFloat() {
        Float subject = 3.14f;

        assertEquals("3.14", gson.toJson(subject));
    }

    @Test
    public void withDouble() {
        Double subject = 3.14;

        assertEquals("3.14", gson.toJson(subject));
    }

    @Test
    public void withBoolean() {
        Boolean subject = false;

        assertEquals("false", gson.toJson(subject));
    }
}
