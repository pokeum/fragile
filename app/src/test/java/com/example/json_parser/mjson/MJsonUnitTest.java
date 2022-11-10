package com.example.json_parser.mjson;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mjson.Json;

public class MJsonUnitTest {

    private String getJsonString() {
        String jsonStr =
        "{" +
            "\"firstName\": \"John\"," +
            "\"lastName\": \"Smith\"," +
            "\"isAlive\": true," +
            "\"age\": 25," +
            "\"address\":" +
                "{" +
                    "\"streetAddress\": \"21 2nd Street\"," +
                    "\"city\": \"New York\"," +
                    "\"state\": \"NY\"," +
                    "\"postalCode\": \"10021-3100\"" +
                "}," +
            "\"phoneNumbers\":" +
                "[" +
                    "{" +
                        "\"type\": \"home\"," +
                        "\"number\": \"212 555-1234\"" +
                    "}," +
                    "{" +
                        "\"type\": \"office\"," +
                        "\"number\": \"646 555-4567\"" +
                    "}" +
                "]," +
            "\"children\": []," +
            "\"spouse\": null" +
        "}";
        return jsonStr;
    }

    @Test
    public void convertJsonStringToJsonObject() {
        Json json = Json.read(getJsonString());
        System.out.println(json);
    }

    @Test
    public void convertJsonStringToJsonArray() {
        Json json = Json.read("[4, 5, {}, true, null, \"ABC\", 6]");
        System.out.println(json);
    }

    @Test
    public void createJsonObject() {
        Json jsonAddress = Json.object(
                "streetAddress", "21 2nd Street",
                "city", "New York",
                "state", "NY",
                "postalCode", "10021-3100"
        );
        Json jsonPhone1 = Json.object(
                "type", "home",
                "number", "212 555-1234"
        );
        Json jsonPhone2 = Json.object(
                "type", "office",
                "number", "646 555-4567"
        );
        Json jsonPerson = Json.object(
                "firstName", "John",
                "lastName", "Smith",
                "isAlive", true,
                "age", 25,
                "address", jsonAddress,
                "phoneNumbers", Json.array(jsonPhone1, jsonPhone2),
                "children", Json.array(),
                "spouse", Json.nil()
        );
        System.out.println(jsonPerson);
    }

    @Test
    public void createJsonObjectFromJavaCollection() {
        List<String> weekdays = Arrays.asList("Sunday", "Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday", "Saturday");
        System.out.println(Json.make(weekdays));

        Map<String, Number> people = new HashMap<>();
        people.put("John", 33);
        people.put("Joan", 27);
        System.out.println(Json.make(people));

        Map<String, String[]> planets = new HashMap<>();
        planets.put("Mercury", null);
        planets.put("Earth", new String[] { "Luna" });
        planets.put("Mars", new String[] { "Phobos", "Deimos" });
        System.out.println(Json.make(planets));
    }

    @Test
    public void classifyFunctionTest() {
        Json json = Json.read(getJsonString());
        System.out.println("Value = " + json.getValue());
        classify(json);
    }

    private void classify(Json jsonObject) {
        if (jsonObject.isArray())
            System.out.println("Array");
        else if (jsonObject.isBoolean())
            System.out.println("Boolean");
        else if (jsonObject.isNull())
            System.out.println("Null");
        else if (jsonObject.isNumber())
            System.out.println("Number");
        else if (jsonObject.isObject())
            System.out.println("Object");
        else if (jsonObject.isString())
            System.out.println("String");
        else if (jsonObject.isPrimitive())
            System.out.println("Primitive");
    }

    @Test
    public void convertJsonObjectToJavaObject() {
        Json json = Json.read(getJsonString());
        Map<String, Object> props = json.asMap();
        for (Map.Entry<String, Object> propEntry: props.entrySet()) {
            System.out.println(propEntry.getKey() + ": " + propEntry.getValue());
        }
    }
}
