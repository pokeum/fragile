package com.example.json_parser.json_library.gson.compare_fragile;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import non_official.pokeum.fragile.Fragile;

public class HtmlEscapeCharacterTest {

    Gson gson = new Gson();
    Fragile fragile = new Fragile();

    @Test
    public void HtmlEscapeTest() {
        String html = getStringFromFile("test.html");

        //System.out.println(html);
        assertEquals(gson.toJson(html), fragile.toJson(html));
    }

    /**
     * Get the Path of the "/src/test/resources" Directory in JUnit
     * https://www.baeldung.com/junit-src-test-resources-directory-path
     */
    private String getStringFromFile(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        String content = "";

        /**
         * Java read file to String
         * https://www.digitalocean.com/community/tutorials/java-read-file-to-string
         */
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            content = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            // TODO("Handle File Not Found Exception")
        } catch (IOException e) {
            // TODO("Handle IO Exception")
        }
        return content;
    }
}
