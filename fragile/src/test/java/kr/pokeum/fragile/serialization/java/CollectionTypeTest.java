package kr.pokeum.fragile.serialization.java;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import kr.pokeum.fragile.Fragile;

public class CollectionTypeTest {

    Fragile fragile = new Fragile();
    Gson gson = new Gson();

    @Test
    public void mapWithNoneStringKey() {
        Map<Integer, String> subject = new HashMap<>();
        subject.put(1, "value1");
        subject.put(null, "value2");
        subject.put(3, "value3");

        assertEquals(gson.toJson(subject), fragile.toJson(subject));
        System.out.println("map with none string key: " + fragile.toJson(subject));
    }
}
