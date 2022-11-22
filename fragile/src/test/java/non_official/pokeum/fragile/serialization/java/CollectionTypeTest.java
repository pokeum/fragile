package non_official.pokeum.fragile.serialization.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import non_official.pokeum.fragile.Fragile;

public class CollectionTypeTest {

    Fragile fragile = new Fragile();

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
                fragile.toJson(subject));
    }
}
