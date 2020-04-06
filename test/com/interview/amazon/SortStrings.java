package com.interview.amazon;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SortStrings {

    public boolean check(String[] words, String order) {
        if (words == null || words.length == 0) return false;
        return divide(words, 0, words.length - 1, order);
    }

    public boolean divide(String[] words, int start, int end, String order) {
        if (start == end) return true;
        int mid = (end - start) / 2 + start;

        boolean d1 = divide(words, start, mid, order);
        boolean d2 = divide(words, mid + 1, end, order);
        if (d1 && d2) {
            return compare(words[mid], words[mid + 1], order);
        } else {
            return false;
        }
    }

    public boolean compare(String str1, String str2, String order) {
        if (str1 == null && str2 == null) return true;
        if (str1 == null || str2 == null) return false;

        int i = 0;
        while (i < str1.length() && i < str2.length()) {
            char c1 = str1.charAt(i), c2 = str2.charAt(i);
            if (c1 == c2) {
                i++;
            } else {
                int idx1 = order.indexOf(c1);
                int idx2 = order.indexOf(c2);

                if (idx1 < idx2)
                    return true;
                else
                    return false;
            }
        }
        if (i == str1.length())
            return true;
        else
            return false;
    }

    @Test
    public void test0() {
        String[] words = {"world", "worlds", "word", "words"};
        String order = "abcworldefghijkmnpqstuvxyz";
        assertTrue(check(words, order));
    }
}
