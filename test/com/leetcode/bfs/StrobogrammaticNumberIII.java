package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Strobogrammatic Number III
 * 
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at
 * upside down).
 * 
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num
 * <= high.
 * 
 * Example:
 * 
 * Input: low = "50", high = "100" Output: 3
 * 
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers. Note: Because the range might be a
 * large number, the low and high numbers are represented as string.
 */

public class StrobogrammaticNumberIII {
    private static final char[][] pairs =
            {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};


    public int strobogrammaticInRange(String low, String high) {
        int res = 0;
        for (int len = low.length(); len <= high.length(); len++) {
            char[] curr = new char[len];
            res += dfs(low, high, curr, 0, len - 1);
        }
        return res;
    }

    public int dfs(String low, String high, char[] curr, int left, int right) {
        if (left > right) {
            String s = new String(curr);
            if ((s.length() == low.length() && s.compareTo(low) < 0)
                    || (s.length() == high.length() && s.compareTo(high) > 0)) {
                return 0;
            }
            return 1;
        }
        int res = 0;
        for (char[] p : pairs) {
            curr[left] = p[0];
            curr[right] = p[1];
            if (curr.length != 1 && curr[0] == '0') {
                continue;
            }
            // 161 => 191, they are not equal
            if (left == right && (p[0] !=p[1])) {
                continue;
            }
            res += dfs(low, high, curr, left + 1, right - 1);
        }
        return res;
    }

    @Test
    public void test0() {
        String low = "50", high = "100";
        assertEquals(3, strobogrammaticInRange(low, high));
    }
}
