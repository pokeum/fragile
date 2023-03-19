package kr.pokeum.fragile.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtils {

    /**
     * Get the Path of the "/src/test/resources" Directory in JUnit
     * https://www.baeldung.com/junit-src-test-resources-directory-path
     */
    public static String getStringFromFile(String fileName) {

        ClassLoader classLoader = TestUtils.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        String content = "";

        /**
         * Java read file to String
         * https://www.digitalocean.com/community/tutorials/java-read-file-to-string
         */
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
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
