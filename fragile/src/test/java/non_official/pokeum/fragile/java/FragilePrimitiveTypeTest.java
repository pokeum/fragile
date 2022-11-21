package non_official.pokeum.fragile.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import non_official.pokeum.fragile.Fragile;

/**
 * Primitive Type: byte, short, int, long, float, double, char, boolean.
 * (+ also test "String" object here)
 */
public class FragilePrimitiveTypeTest {

    Fragile fragile = new Fragile();

    @Test
    public void withChar() {
        Character subject = 'a';

        assertEquals("\"a\"", fragile.toJson(subject));
    }

    @Test
    public void withString() {
        String subject = "GSON";

        assertEquals("\"GSON\"", fragile.toJson(subject));
    }

    @Test
    public void withByte() {
        Byte subject = 12;

        assertEquals("12", fragile.toJson(subject));
    }

    @Test
    public void withShort() {
        Short subject = 123;

        assertEquals("123", fragile.toJson(subject));
    }

    @Test
    public void withInteger() {
        Integer subject = 1234;

        assertEquals("1234", fragile.toJson(subject));
    }

    @Test
    public void withLong() {
        Long subject = 12345L;

        assertEquals("12345", fragile.toJson(subject));
    }

    @Test
    public void withFloat() {
        Float subject = 3.14f;

        assertEquals("3.14", fragile.toJson(subject));
    }

    @Test
    public void withDouble() {
        Double subject = 3.14;

        assertEquals("3.14", fragile.toJson(subject));
    }

    @Test
    public void withBoolean() {
        Boolean subject = false;

        assertEquals("false", fragile.toJson(subject));
    }
}
