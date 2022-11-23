package non_official.pokeum.fragile.serialization.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import non_official.pokeum.fragile.Fragile;

/**
 * Primitive Type: byte, short, int, long, float, double, char, boolean.
 * (+ also test "String" object here)
 */
public class PrimitiveTypeTest {

    Fragile fragile = new Fragile();

    @Test
    public void withChar() {
        char subject = 'a';

        assertEquals("\"a\"", fragile.toJson(subject));
    }

    @Test
    public void withString() {
        String subject = "FRAGILE";

        assertEquals("\"FRAGILE\"", fragile.toJson(subject));
    }

    @Test
    public void withByte() {
        byte subject = 12;

        assertEquals("12", fragile.toJson(subject));
    }

    @Test
    public void withShort() {
        short subject = 123;

        assertEquals("123", fragile.toJson(subject));
    }

    @Test
    public void withInteger() {
        int subject = 1234;

        assertEquals("1234", fragile.toJson(subject));
    }

    @Test
    public void withLong() {
        long subject = 12345L;

        assertEquals("12345", fragile.toJson(subject));
    }

    @Test
    public void withFloat() {
        float subject = 3.14f;

        assertEquals("3.14", fragile.toJson(subject));
    }

    @Test
    public void withDouble() {
        double subject = 3.14;

        assertEquals("3.14", fragile.toJson(subject));
    }

    @Test
    public void withBoolean() {
        boolean subject = false;

        assertEquals("false", fragile.toJson(subject));
    }
}
