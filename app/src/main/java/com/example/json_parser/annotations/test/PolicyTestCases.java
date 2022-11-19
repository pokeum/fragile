package com.example.json_parser.annotations.test;

//import com.example.json_parser.annotations.annotation.java.TestCase;
import com.example.json_parser.annotations.annotation.kotlin.TestCase;

import java.io.IOException;

public class PolicyTestCases {

    // Must throw IOException
    @TestCase(willThrow = IOException.class)
    public static void testCase1() {
        // Code goes here
    }

    // We are not expecting any exception
    public static void testCase2() {
        // Code goes here
    }
}
