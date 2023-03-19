package kr.pokeum.fragile.serialization.java;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

import kr.pokeum.fragile.Fragile;

/**
 * Primitive Type: byte, short, int, long, float, double, char, boolean.
 * (+ also test "String" object here)
 */
public class PrimitiveTypeTest {

    Fragile fragile = new Fragile();
    Gson gson = new Gson();

    @Test
    public void withChar() {
        char subject = 'a';

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withString() {
        String subject = "FRAGILE";

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withByte() {
        byte subject = 12;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withShort() {
        short subject = 123;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withInteger() {
        int subject = 1234;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withLong() {
        long subject = 12345L;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withFloat() {
        float subject = 3.14f;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withDouble() {
        double subject = 3.14;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    @Test
    public void withBoolean() {
        boolean subject = false;

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }
}
