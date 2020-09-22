package com.interview.oci;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SubstringPermutation {

    @Test
    public void test0() {
        String s1 = "ab", s2 = "uibass";
        assertTrue(containPermutation(s1, s2));
    }
    
    @Test
    public void test1() {
        String s1 = "ab", s2 = "uiboass";
        assertFalse(containPermutation(s1, s2));
    }
    @Test
    public void test2() {
        String s1 = "abc", s2 = "dbac";
        assertTrue(containPermutation(s1, s2));
    }
    
    public boolean containPermutation(String s1, String s2) {
        int[] count = new int[26];
        for (char ch : s1.toCharArray()) {
            count[ch - 'a']++;
        }

        int res = s1.length();
        for (int left = 0, right = 0; right < s2.length(); ++right) {
            char ch = s2.charAt(right);
            count[ch - 'a']--;
            if (count[ch - 'a'] >= 0) {
                res--;
                if (res == 0) {
                    return true;
                }
            }

            if (right - left == s1.length() - 1) {
                char kick = s2.charAt(left);
                count[kick - 'a']++;
                if(count[kick- 'a'] > 0) {
                    res++;
                }
                left++;
            }
        }
        return false;
    }

}
