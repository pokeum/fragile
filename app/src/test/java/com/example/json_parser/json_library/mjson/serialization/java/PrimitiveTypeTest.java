package com.example.json_parser.json_library.mjson.serialization.java;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import mjson.Json;

/**
 * Primitive Type: byte, short, int, long, float, double, char, boolean.
 * (+ also test "String" object here)
 */
public class PrimitiveTypeTest {

    @Test
    @Ignore("[ERROR] Don't know how to convert to Json : a")
    public void withChar() {
        char subject = 'a';

        assertEquals('a', Json.make(subject).asChar());
    }

    @Test
    public void withStringGetChar() {
        String subject = "a";

        assertEquals('a', Json.make(subject).asChar());
    }

    @Test
    public void withString() {
        String subject = "GSON";

        // mjson.Json$StringJson
        assertEquals("GSON", Json.make(subject).asString());
    }

    @Test
    public void withByte() {
        byte subject = 12;

        assertEquals(12, Json.make(subject).asByte());
    }

    @Test
    public void withShort() {
        short subject = 123;

        assertEquals(123, Json.make(subject).asShort());
    }

    @Test
    public void withInteger() {
        int subject = 1234;

        assertEquals(1234, Json.make(subject).asInteger());
    }

    @Test
    public void withLong() {
        long subject = 12345L;

        assertEquals(12345, Json.make(subject).asLong());
    }

    @Test
    public void withFloat() {
        float subject = 3.14f;

        float expected = 3.14f;
        float result = Json.make(subject).asFloat();

        assertEquals(Float.floatToIntBits(expected), Float.floatToIntBits(result));
    }

    @Test
    public void withDouble() {
        double subject = 3.14;

        double expected = 3.14;
        double result = Json.make(subject).asDouble();

        assertEquals(Double.doubleToLongBits(expected), Double.doubleToLongBits(result));
    }

    @Test
    public void withBoolean() {
        boolean subject = false;

        assertEquals(false, Json.make(subject).asBoolean());
    }
}
