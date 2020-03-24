package com.leetcode.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class VerifyingAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            char ch = order.charAt(i);
            map[ch - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; ++i) {
            String a = words[i], b = words[i + 1];

            int index = 0;
            while (index < a.length() && index < b.length()) {
                if (map[a.charAt(index) - 'a'] < map[b.charAt(index) - 'a']) break;
                if (map[a.charAt(index) - 'a'] > map[b.charAt(index) - 'a']) return false;
                index++;
            }
            if (index < a.length() && index == b.length()) return false;
        }
        return true;
    }

    @Test
    public void test0() {
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        assertTrue(isAlienSorted(words, order));
    }
    

    @Test
    public void test1() {
        String[] words = {"fxasxpc","dfbdrifhp","nwzgs"};
        String order = "zkgwaverfimqxbnctdplsjyohu";
        assertFalse(isAlienSorted(words, order));
    }
}
