package com.example.json_parser.json_library.gson.compare_fragile;

import static org.junit.Assert.assertEquals;

import com.example.json_parser.util.TestUtils;
import com.google.gson.Gson;

import org.junit.Test;

import non_official.pokeum.fragile.Fragile;

public class HtmlEscapeCharacterTest {

    Gson gson = new Gson();
    Fragile fragile = new Fragile();

    @Test
    public void HtmlEscapeTest() {
        String html = TestUtils.getStringFromFile("test.html");

        //System.out.println(html);
        assertEquals(gson.toJson(html), fragile.toJson(html));
    }
}
