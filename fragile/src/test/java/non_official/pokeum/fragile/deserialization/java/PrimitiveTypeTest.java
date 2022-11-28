package non_official.pokeum.fragile.deserialization.java;

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
        char expected = 'a';

        String json = "\"a\"";
        char result = fragile.fromJson(json).asString().charAt(0);
        assertEquals(expected, result);
    }

    @Test
    public void withString() {
        String expected = "FRAGILE";

        String json = "\"FRAGILE\"";
        String result = fragile.fromJson(json).asString();
        assertEquals(expected, result);
    }

    @Test
    public void withByte() {
        byte expected = 12;

        String json = "12";
        byte result = fragile.fromJson(json).asNumber().byteValue();
        assertEquals(expected, result);
    }

    @Test
    public void withShort() {
        short expected = 123;

        String json = "123";
        short result = fragile.fromJson(json).asNumber().shortValue();
        assertEquals(expected, result);
    }

    @Test
    public void withInteger() {
        int expected = 1234;

        String json = "1234";
        int result = fragile.fromJson(json).asNumber().intValue();
        assertEquals(expected, result);
    }

    @Test
    public void withLong() {
        long expected = 12345L;

        String json = "12345";
        long result = fragile.fromJson(json).asNumber().longValue();
        assertEquals(expected, result);
    }

    @Test
    public void withFloat() {
        float expected = 3.14f;

        String json = "3.14";
        float result = fragile.fromJson(json).asNumber().floatValue();
        assertEquals(Float.floatToIntBits(expected), Float.floatToIntBits(result));
    }

    @Test
    public void withDouble() {
        double expected = 3.14;

        String json = "3.14";
        double result = fragile.fromJson(json).asNumber().doubleValue();
        assertEquals(Double.doubleToLongBits(expected), Double.doubleToLongBits(result));
    }

    @Test
    public void withBoolean() {
        boolean expected = false;

        String json = "false";
        boolean result = fragile.fromJson(json).asBoolean();
        assertEquals(expected, result);
    }
}
