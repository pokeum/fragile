package kr.pokeum.fragile.serialization.java;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kr.pokeum.fragile.Fragile;
import kr.pokeum.fragile.FragileBuilder;

public class SerializeNullsTest {

    Fragile fragileDisableSerializeNulls = new FragileBuilder().serializeNulls(false).build();
    Fragile fragileEnableSerializeNulls = new FragileBuilder().serializeNulls(true).build();

    Gson gsonDisableSerializeNulls = new GsonBuilder().create();
    Gson gsonEnableSerializeNulls = new GsonBuilder().serializeNulls().create();

    @Test
    //@Ignore("NO DIFFERENCE")
    public void withArray() {
        String[] subject = new String[] { "hello", null, "world" };

        // [ "hello", null, "world" ]
        assertEquals(
                gsonDisableSerializeNulls.toJson(subject),
                fragileDisableSerializeNulls.toJson(subject)
        );
        // [ "hello", null, "world" ]
        assertEquals(
                gsonEnableSerializeNulls.toJson(subject),
                fragileEnableSerializeNulls.toJson(subject)
        );
    }

    @Test
    //@Ignore("NO DIFFERENCE")
    public void withSet() {
        Set<Integer> subject = new HashSet();
        subject.add(1);
        subject.add(null);
        subject.add(2);

        // [ null, 1, 2 ]
        assertEquals(
                gsonDisableSerializeNulls.toJson(subject),
                fragileDisableSerializeNulls.toJson(subject)
        );
        // [ null, 1, 2 ]
        assertEquals(
                gsonEnableSerializeNulls.toJson(subject),
                fragileEnableSerializeNulls.toJson(subject)
        );
    }

    @Test
    //@Ignore("NO DIFFERENCE")
    public void withList() {
        List<Double> subject = new LinkedList();
        subject.add(1.23);
        subject.add(null);
        subject.add(4.56);

        // [ 1.23, null, 4.56 ]
        assertEquals(
                gsonDisableSerializeNulls.toJson(subject),
                fragileDisableSerializeNulls.toJson(subject)
        );
        // [ 1.23, null, 4.56 ]
        assertEquals(
                gsonEnableSerializeNulls.toJson(subject),
                fragileEnableSerializeNulls.toJson(subject)
        );
    }

    @Test
    public void withMap() {
        Map<String, String> subject = new HashMap<>();
        subject.put("key1", "value1");
        subject.put("key2", null);
        subject.put("key3", "value3");

        // { "key1": "value1", "key3": "value3" }
        assertEquals(
                gsonDisableSerializeNulls.toJson(subject),
                fragileDisableSerializeNulls.toJson(subject)
        );
        // { "key1": "value1", "key2": null, "key3": "value3" }
        assertEquals(
                gsonEnableSerializeNulls.toJson(subject),
                fragileEnableSerializeNulls.toJson(subject)
        );
    }
}
