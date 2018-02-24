package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];

        // store the longest contiguous substring ending at current position
        int maxLengthCur = 0;

        for (int i = 0; i < p.length(); ++i) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i - 1) - p.charAt(i) == 25)) {
                maxLengthCur++;
            } else {
                maxLengthCur = 1;
            }

            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }

        int sum = 0;
        for (int i = 0; i < 26; ++i) {
            sum += count[i];
        }
        return sum;
    }

    @Test
    public void test0() {
        String p = "zab";
        assertEquals(Integer.valueOf(6), Integer.valueOf(findSubstringInWraproundString(p)));
    }
}
