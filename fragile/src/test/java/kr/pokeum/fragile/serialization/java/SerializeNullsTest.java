package kr.pokeum.fragile.serialization.java;

import static org.junit.Assert.assertEquals;

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

    Fragile disableSerializeNulls = new FragileBuilder().serializeNulls(false).build();
    Fragile enableSerializeNulls = new FragileBuilder().serializeNulls(true).build();

    @Test
    //@Ignore("NO DIFFERENCE")
    public void withArray() {
        String[] subject = new String[] { "hello", null, "world" };

        assertEquals("[\"hello\",null,\"world\"]", disableSerializeNulls.toJson(subject));
        assertEquals("[\"hello\",null,\"world\"]", enableSerializeNulls.toJson(subject));
    }

    @Test
    //@Ignore("NO DIFFERENCE")
    public void withSet() {
        Set<Integer> subject = new HashSet<Integer>();
        subject.add(1);
        subject.add(null);
        subject.add(2);

        assertEquals("[null,1,2]", disableSerializeNulls.toJson(subject));
        assertEquals("[null,1,2]", enableSerializeNulls.toJson(subject));
    }

    @Test
    //@Ignore("NO DIFFERENCE")
    public void withList() {
        List<Double> subject = new LinkedList<>();
        subject.add(1.23);
        subject.add(null);
        subject.add(4.56);

        assertEquals("[1.23,null,4.56]", disableSerializeNulls.toJson(subject));
        assertEquals("[1.23,null,4.56]", enableSerializeNulls.toJson(subject));
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
