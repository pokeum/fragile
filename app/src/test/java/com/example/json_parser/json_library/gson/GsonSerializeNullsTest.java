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

    Gson gsonDisableSerializeNulls = new GsonBuilder().create();
    Gson gsonEnableSerializeNulls = new GsonBuilder().serializeNulls().create();

    @Test
    public void withArray() {
        String[] subject = new String[] { "hello", null, "world" };

        assertEquals("[\"hello\",null,\"world\"]", gsonDisableSerializeNulls.toJson(subject));
        assertEquals("[\"hello\",null,\"world\"]", gsonEnableSerializeNulls.toJson(subject));
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

        assertEquals("[null,1,2]", gsonDisableSerializeNulls.toJson(subject));
        assertEquals("[null,1,2]", gsonEnableSerializeNulls.toJson(subject));
    }

    @Test
    public void withMap() {
        Map<String, String> subject = new HashMap<>();
        subject.put("key1", "value1");
        subject.put("key2", null);
        subject.put("key3", "value3");

        assertEquals("{\"key1\":\"value1\",\"key3\":\"value3\"}",
                gsonDisableSerializeNulls.toJson(subject));

        assertEquals("{\"key1\":\"value1\",\"key2\":null,\"key3\":\"value3\"}",
                gsonEnableSerializeNulls.toJson(subject));
    }


    //System.out.println("Disable Serialize Nulls: " + gsonDisableSerializeNulls.toJson(subject));
    //System.out.println("Enable Serialize Nulls: " + gsonEnableSerializeNulls.toJson(subject));
}