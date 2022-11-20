package com.example.annotations.test;

import com.example.annotations.annotation.java.Version;

@Version(major = 1, minor = 19, patch = 1)
public class AccessAnnotation {

    @Version(major = 1, minor = 2, patch = 3)
    public void testMethod1() {
        // Code Goes Here
    }

    @Version(major = 1, minor = 2)
    @Deprecated
    public void testMethod2() {
        // Code Goes Here
    }
}
