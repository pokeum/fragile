package kr.pokeum.fragile.util;

import java.util.ArrayList;
import java.util.List;

public class JavaConverter {

    public static String convert(String value) { return value; }

    public static <T> T[] convert(T[] value) { return value; }

    public static <T> List<T> convert(List<T> value) { return value; }
    public static <T> ArrayList<T> convert(ArrayList<T> value) { return value; }
}
