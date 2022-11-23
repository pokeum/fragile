package com.example.json_parser.json_library.gson.serialization.java;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

/**
 * Primitive Type: byte, short, int, long, float, double, char, boolean.
 * (+ also test "String" object here)
 */
public class PrimitiveTypeTest {

    Gson gson = new Gson();

    @Test
    public void withChar() {
        char subject = 'a';

        assertEquals("\"a\"", gson.toJson(subject));
    }

    @Test
    public void withString() {
        String subject = "GSON";

        assertEquals("\"GSON\"", gson.toJson(subject));
    }

    @Test
    public void withByte() {
        byte subject = 12;

        assertEquals("12", gson.toJson(subject));
    }

    @Test
    public void withShort() {
        short subject = 123;

        assertEquals("123", gson.toJson(subject));
    }

    @Test
    public void withInteger() {
        int subject = 1234;

        assertEquals("1234", gson.toJson(subject));
    }

    @Test
    public void withLong() {
        long subject = 12345L;

        assertEquals("12345", gson.toJson(subject));
    }

    @Test
    public void withFloat() {
        float subject = 3.14f;

        assertEquals("3.14", gson.toJson(subject));
    }

    @Test
    public void withDouble() {
        double subject = 3.14;

        assertEquals("3.14", gson.toJson(subject));
    }

    @Test
    public void withBoolean() {
        boolean subject = false;

        assertEquals("false", gson.toJson(subject));
    }
}
