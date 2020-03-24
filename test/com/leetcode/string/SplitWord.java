package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SplitWord {
    public List<String> split(String word) {
        List<String> res = new ArrayList<>();
        if (word == null || word.isEmpty()) return res;
        StringBuilder sb = new StringBuilder();
        boolean prevChar = isWordChar(word.charAt(0));
        sb.append(word.charAt(0));

        for (int i = 1; i <= word.length(); ++i) {
            if (i == word.length()) {
                res.add(sb.toString());
                break;
            }
            char ch = word.charAt(i);
            boolean currChar = isWordChar(ch);
            if (prevChar ^ currChar) {
                res.add(sb.toString());
                sb.setLength(0);
                prevChar = currChar;
            }
            sb.append(ch);
        }

        return res;
    }

    private boolean isWordChar(char ch) {
        return Character.isDigit(ch) || Character.isAlphabetic(ch);
    }

    @Test
    public void test0() {
        String word = "a&*b2";
        List<String> expected = Arrays.asList("a", "&*", "b2");
        assertEquals(expected, split(word));
    }

    @Test
    public void test1() {
        String word = "   a& *b2";
        List<String> expected = Arrays.asList("   ", "a", "& *", "b2");
        assertEquals(expected, split(word));
    }
}
