package com.example.json_parser.json_library.gson.compare_fragile;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Ignore;
import org.junit.Test;

import kr.pokeum.fragile.Fragile;

public class EscapeCharacterTest {

    Gson gson = new Gson();
    Fragile fragile = new Fragile();

    /**
     * quotation mark (")
     */
    @Test
    public void withQuotationMark() {
        String subject = "quotation mark: |\"|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * reverse solidus (\)
     */
    @Test
    public void withReverseSolidus() {
        String subject = "reverse solidus: |\\|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * solidus (/): NOT A ESCAPE SEQUENCE
     */
    @Ignore
    @Test
    public void withSolidus() {
        String subject = "solidus: |/|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * backspace
     */
    @Test
    public void withBackspace() {
        String subject = "backspace: |\b|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * newline
     */
    @Test
    public void withNewline() {
        String subject = "newline: |\n|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * carriage return
     */
    @Test
    public void withCarriageReturn() {
        String subject = "carriage return: |\r|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * horizontal tab
     */
    @Test
    public void withHorizontalTab() {
        String subject = "horizontal tab: |\t|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * formfeed
     */
    @Test
    public void withFormfeed() {
        String subject = "formfeed: |\f|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }

    /**
     * 4 hexadecimal digits
     */
    @Test
    public void with4HexadecimalDigits() {
        String subject = "4 hexadecimal digits: |\u0001|";

        System.out.println(subject);
        System.out.println("[Gson] " + gson.toJson(subject));
        System.out.println("[Fragile] " + fragile.toJson(subject));
        assertEquals(gson.toJson(subject), fragile.toJson(subject));
    }
}
