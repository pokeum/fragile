package com.example.json_parser.json_library.gson.compare_fragile;

import static org.junit.Assert.assertEquals;

import com.example.json_parser.model.java.Car;
import com.google.gson.Gson;

import org.junit.Test;

import non_official.pokeum.fragile.Fragile;

public class BasicObjectTest {

    Gson gson = new Gson();
    Fragile fragile = new Fragile();

    @Test
    public void withCar() {
        Car car = new Car("My Car's Name", 15, 1, 4,
                "North", "Red", "Gas", 2.1795e4);

        System.out.println("[Gson] " + gson.toJson(car));
        System.out.println("[Fragile] " + fragile.toJson(car));
        assertEquals(gson.toJson(car), fragile.toJson(car));
    }
}
