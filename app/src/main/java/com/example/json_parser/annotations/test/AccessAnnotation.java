package com.example.json_parser.annotations.test;

import com.example.json_parser.annotations.annotation.java.Version;

@Version(major = 1)
public class AccessAnnotation {

    @Version(major = 1, minor = 1)
    public void testMethod1() {
        // Code Goes Here
    }

    @Version(major = 1, minor = 2)
    @Deprecated
    public void testMethod2() {
        // Code Goes Here
    }
}
