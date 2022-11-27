package com.example.json_parser.json_library.org_json.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import com.example.json_parser.util.TestUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * JSONObject: unordered collection of key and value pairs
 */
public class JSONObjectTest {

    private JSONObject createJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");
        return jo;
    }

    private JSONObject createJSONObjectFromJSONString() throws JSONException {
        String jsonString = TestUtils.getStringFromFile("cake.json");
        JSONObject jo = new JSONObject(jsonString);
        return jo;
    }

    @Test
    public void get() {
        try {
            JSONObject jo = createJSONObject();
            assertEquals("chicago", jo.get("city"));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getJSONObject() {
        try {
            JSONObject jo = createJSONObjectFromJSONString();
            Exception exception = assertThrows(JSONException.class, () -> {
                jo.getJSONObject("id");
            });
            String expectedMessage = "JSONObject[\"id\"] is not a JSONObject.";
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * throws JSONException if the key is not found
     */
    @Test
    public void getNonExisting() {
        try {
            JSONObject jo = createJSONObject();
            Exception exception = assertThrows(JSONException.class, () -> {
                jo.get("gender");
            });
            String expectedMessage = "JSONObject[\"gender\"] not found.";
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void opt() {
        try {
            JSONObject jo = createJSONObject();
            assertEquals("chicago", jo.opt("city"));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * return null if the key is not found
     */
    @Test
    public void optNonExisting() {
        try {
            JSONObject jo = createJSONObject();
            assertNull(jo.opt("gender"));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void putFunction() {

    }
}
