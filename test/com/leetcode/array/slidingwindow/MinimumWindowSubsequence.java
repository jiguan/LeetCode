package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MinimumWindowSubsequence {
    public String minWindow1(String S, String T) {
        int index = -1;
        String res = "";
        while (true) {
            for (char ch : T.toCharArray()) {
                index = S.indexOf(ch, index + 1);
                if (index == -1) return res;
            }
            // find the index of last letter of T in S
            int end = ++index;
            // find the index of first letter of T in S
            for (int j = T.length() - 1; j >= 0; j--) {
                // index - 1 to define new end boundary
                // to make it work for the first time, index was increased previously
                index = S.lastIndexOf(T.charAt(j), index - 1);
            }
            if (res == "" || res.length() > end - index) {
                res = S.substring(index, end);
                // define a new end boundary
                index++;
            }
        }
    }

    public String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        String res = "";
        int i = 0, j = 0, len = Integer.MAX_VALUE;
        while (i < s.length) {
            if (s[i] == t[j]) {
                j++;
                if (j == t.length) { // all chars in T has been matched
                    int end = i + 1; // i is the last match index in S
                    j--; // now tindex is the last index in T
                    while (j >= 0) { // both i and tindex move back
                        if (s[i] == t[j]) {
                            j--;
                        }
                        i--;
                    }
                    i++; // we found the first match index in S
                    j = 0;
                    if (end - i < len) { // optimization ops
                        len = end - i;
                        res = S.substring(i, end); // [i, end) is the matching subsequence
                    }
                }
            }
            i++;
        }
        return len == Integer.MAX_VALUE ? "" : res;
    }

    @Test
    public void test0() {
        String S = "abcdebdde", T = "bde";
        assertEquals("bcde", minWindow1(S, T));
    }

    @Test
    public void test1() {
        String S = "cnhczmccqouqadqtmjjzl", T = "mm";
        assertEquals("mccqouqadqtm", minWindow1(S, T));
    }
}
