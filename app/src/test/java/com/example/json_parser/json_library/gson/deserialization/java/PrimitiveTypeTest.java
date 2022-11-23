package com.example.json_parser.json_library.gson.deserialization.java;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

public class PrimitiveTypeTest {

    Gson gson = new Gson();

    @Test
    public void withChar() {
        char expected = 'a';

        String json = "\"a\"";
        char result = gson.fromJson(json, Character.class);
        assertEquals(expected, result);
    }

    @Test
    public void withString() {
        String expected = "GSON";

        String json = "\"GSON\"";
        String result = gson.fromJson(json, String.class);
        assertEquals(expected, result);
    }
}
