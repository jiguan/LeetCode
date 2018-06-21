package com.hackerank.test;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class DuplicateWords {

    public String removeDuplicate(String s) {
        String regex = "\\b(\\w+)(\\W+\\1\\b)+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        while (m.find()) {
            s = s.replace(m.group(), m.group(1));
        }
        return s;
    }

    @Test
    public void test0() {
        String input = "Goodbye bye bye world world world";
        String expected = "Goodbye bye world";
        assertEquals(expected, removeDuplicate(input));
    }

}
