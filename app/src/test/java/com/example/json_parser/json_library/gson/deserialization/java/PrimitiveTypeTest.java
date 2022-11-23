package com.example.json_parser.json_library.gson.deserialization.java;

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
        char expected = 'a';

        String json = "\"a\"";
        char result = gson.fromJson(json, char.class);
        assertEquals(expected, result);
    }

    @Test
    public void withString() {
        String expected = "GSON";

        String json = "\"GSON\"";
        String result = gson.fromJson(json, String.class);
        assertEquals(expected, result);
    }

    @Test
    public void withByte() {
        byte expected = 12;

        String json = "12";
        byte result = gson.fromJson(json, byte.class);
        assertEquals(expected, result);
    }

    @Test
    public void withShort() {
        short expected = 123;

        String json = "123";
        short result = gson.fromJson(json, short.class);
        assertEquals(expected, result);
    }

    @Test
    public void withInteger() {
        int expected = 1234;

        String json = "1234";
        int result = gson.fromJson(json, int.class);
        assertEquals(expected, result);
    }

    @Test
    public void withLong() {
        long expected = 12345L;

        String json = "12345";
        long result = gson.fromJson(json, long.class);
        assertEquals(expected, result);
    }

    @Test
    public void withFloat() {
        float expected = 3.14f;

        String json = "3.14";
        float result = gson.fromJson(json, float.class);
        assertEquals(Float.floatToIntBits(expected), Float.floatToIntBits(result));
    }

    @Test
    public void withDouble() {
        double expected = 3.14;

        String json = "3.14";
        double result = gson.fromJson(json, double.class);
        assertEquals(Double.doubleToLongBits(expected), Double.doubleToLongBits(result));
    }

    @Test
    public void withBoolean() {
        boolean expected = false;

        String json = "false";
        boolean result = gson.fromJson(json, boolean.class);
        assertEquals(expected, result);
    }
}
