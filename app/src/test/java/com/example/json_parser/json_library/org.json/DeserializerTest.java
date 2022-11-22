package com.example.json_parser.json_library.org.json;

import static org.junit.Assert.assertEquals;

import com.example.json_parser.model.java.Car;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

public class DeserializerTest {

    @Test
    @Ignore("[ERROR] A JSONObject text must begin with '{' at ...")
    public void withString() {
        String jsonString = "\"ORG.JSON\"";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    @Test
    public void withObject() {
        Car car = new Car("My Car's Name", 15, 1, 4,
                "North", "Red", "Gas", 2.1795e4);
        String jsonString = new Gson().toJson(car);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            assertEquals("My Car's Name", jsonObject.getString("name"));
        } catch (JSONException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
