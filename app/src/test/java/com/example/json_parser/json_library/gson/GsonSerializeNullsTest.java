package com.example.json_parser.json_library.gson;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GsonSerializeNullsTest {

    Gson disableSerializeNulls = new GsonBuilder().create();
    Gson enableSerializeNulls = new GsonBuilder().serializeNulls().create();

    @Test
    public void withArray() {
        String[] subject = new String[] { "hello", null, "world" };

        assertEquals("[\"hello\",null,\"world\"]", disableSerializeNulls.toJson(subject));
        assertEquals("[\"hello\",null,\"world\"]", enableSerializeNulls.toJson(subject));
    }

    @Test
    public void withSet() {
        Set<Integer> subject = new HashSet<Integer>();
        subject.add(1);
        subject.add(null);
        subject.add(2);

        /** "Java Double Brace Initialization" --> NOT WORKING PROPERLY!
        Set<Integer> subject = new HashSet<Integer>() {{
            add(1);
            add(null);
            add(2);
         }};
        */

        assertEquals("[null,1,2]", disableSerializeNulls.toJson(subject));
        assertEquals("[null,1,2]", enableSerializeNulls.toJson(subject));
    }

    @Test
    public void withMap() {
        Map<String, String> subject = new HashMap<>();
        subject.put("key1", "value1");
        subject.put("key2", null);
        subject.put("key3", "value3");

        assertEquals("{\"key1\":\"value1\",\"key3\":\"value3\"}",
                disableSerializeNulls.toJson(subject));

        assertEquals("{\"key1\":\"value1\",\"key2\":null,\"key3\":\"value3\"}",
                enableSerializeNulls.toJson(subject));
    }
}