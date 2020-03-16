package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertArrayEquals;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if (p.length() > s.length()) return res;

        // char - times
        int[] count = new int[26];
        for (char ch : p.toCharArray()) {
            ++count[ch - 'a'];
        }

        int diff = p.length();
        for (int start = 0, end = 0; end < s.length(); ++end) {
            char ch = s.charAt(end);
            count[ch - 'a']--;
            if (count[ch - 'a'] >= 0) {
                diff--;
            }
            if (diff == 0) {
                res.add(start);
            }
            if (end - start == p.length() - 1) {
                char kickout = s.charAt(start);
                start++;
                count[kickout - 'a']++;
                if (count[kickout - 'a'] > 0) diff++;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "cbaebabacd", p = "abc";
        List<Integer> list = findAnagrams(s, p);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            actual[i] = list.get(i);
        }
        assertArrayEquals(new int[] {0, 6}, actual);
    }

    @Test
    public void test1() {
        String s = "acdcaeccde", p = "c";
        List<Integer> list = findAnagrams(s, p);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            actual[i] = list.get(i);
        }
        assertArrayEquals(new int[] {1, 3, 6, 7}, actual);
    }

}
