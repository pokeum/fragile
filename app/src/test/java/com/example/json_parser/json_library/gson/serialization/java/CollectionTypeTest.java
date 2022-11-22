package com.example.json_parser.json_library.gson.serialization.java;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Array, List, Set, and Map Test
 */
public class CollectionTypeTest {

    Gson gson = new Gson();

    //================ Array ================//

    //================ List =================//

    //================ Set ==================//

    //================ Map ==================//
    @Test
    public void mapWithNoneStringKey() {
        Map<Integer, String> subject = new HashMap<>();
        subject.put(1, "value1");
        subject.put(null, "value2");
        subject.put(3, "value3");

        assertEquals("{\"null\":\"value2\",\"1\":\"value1\",\"3\":\"value3\"}",
                gson.toJson(subject));
    }
}
