package com.example.json_parser.json_library.org_json.structure.json_object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.json_parser.util.TestUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Returns the value mapped by name, or null if no such mapping exists.
 */
public class OptTest {

    private JSONObject createJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", 22);
        jo.put("city", "chicago");
        return jo;
    }

    private JSONObject createJSONObjectFromJSONString() throws JSONException {
        String jsonString = TestUtils.getStringFromFile("cake.json");
        JSONObject jo = new JSONObject(jsonString);
        return jo;
    }

    @Test
    public void opt() {
        try {
            JSONObject jo = createJSONObject();
            assertEquals(22, jo.opt("age"));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void optNonExisting() {
        try {
            JSONObject jo = createJSONObject();
            assertNull(jo.opt("gender"));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
}
