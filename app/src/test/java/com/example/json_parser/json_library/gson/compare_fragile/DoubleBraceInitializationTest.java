package com.example.json_parser.json_library.gson.compare_fragile;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import non_official.pokeum.fragile.Fragile;

public class DoubleBraceInitializationTest {

    Gson gson = new Gson();
    Fragile fragile = new Fragile();

    @Test
    public void withSet_1Depth() {
        Set<Integer> subject = new HashSet<Integer>() {{
            add(1);
            add(null);
            add(2);
        }};

        assertEquals("null", gson.toJson(subject));
        assertEquals("[null,1,2]", fragile.toJson(subject));
    }
}
