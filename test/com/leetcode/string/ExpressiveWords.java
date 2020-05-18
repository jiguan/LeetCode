package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi"
 * -> "hiiii". In these strings like "heeellooo", we have groups of adjacent letters that are all
 * the same: "h", "eee", "ll", "ooo".
 * 
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any
 * number of applications of the following extension operation: choose a group consisting of
 * characters c, and add some number of characters c to the group so that the size of the group is 3
 * or more.
 * 
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
 * but we cannot get "helloo" since the group "oo" has size less than 3. Also, we could do another
 * extension like "ll" -> "lllll" to get "helllllooo". If S = "helllllooo", then the query word
 * "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo"
 * -> "helllllooo" = S.
 * 
 * Given a list of query words, return the number of words that are stretchy.
 */
public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String word : words) {
            if (isStretchy(S, word)) {
                res++;
            }
        }
        return res;
    }

    private boolean isStretchy(String S, String word) {
        int i = 0, j = 0;
        while (i < S.length() && j < word.length()) {
            if (S.charAt(i) == word.charAt(j)) {
                int len1 = sameLetterLength(S, i);
                int len2 = sameLetterLength(word, j);
                if (len1 == len2 || (len2 < len1 && len1 >= 3)) {
                    i += len1;
                    j += len2;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }

    private int sameLetterLength(String s, int index) {
        int len = 0;
        for (int i = index; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(index)) {
                len++;
            } else {
                break;
            }
        }
        return len;
    }

    @Test
    public void test0() {
        String S = "heeellooo";
        String[] words = {"hello", "hi", "helo"};
        assertEquals(1, expressiveWords(S, words));
    }

    @Test
    public void test1() {
        String S = "heeellooo";
        String[] words = {"ohell"};
        assertEquals(0, expressiveWords(S, words));
    }
}
