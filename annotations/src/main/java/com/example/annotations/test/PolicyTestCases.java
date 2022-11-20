package com.example.annotations.test;

//import com.example.annotations.annotation.kotlin.TestCase;
import com.example.annotations.annotation.java.TestCase;

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
