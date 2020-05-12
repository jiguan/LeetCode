package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

// 0, 1, 6, 8, 9 => 0, 1, 9, 8, 6
// 2, 3, 4, 5 and 7 => invalid
// A confusing number is a number that when rotated 180 degrees becomes a different number with each
// digit valid
// Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
public class ConfusingNumberII {
    int[] keys = new int[] {0, 1, 6, 8, 9};
    int[] vals = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    // time: O(5^m), m is length of N
    // There is a faster version
    public int confusingNumberII(int N) {
        return dfs(0, N);
    }

    // return how many valid
    private int dfs(long curr, int N) {
        int res = 0;

        if (isConfusingNum(curr)) {
            res++;
        }

        for (int num : keys) {
            long next = curr * 10 + num;

            if (next > 0 && next <= N) {
                res += dfs(next, N);
            }
        }
        return res;
    }

    private boolean isConfusingNum(long num) {
        String s_num = String.valueOf(num);
        int i = 0, j = s_num.length() - 1;
        while (i <= j) {
            if (s_num.charAt(i) - '0' == vals[s_num.charAt(j) - '0']) {
                i++;
                j--;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testIsConfusingNum() {
        assertFalse(isConfusingNum(0));
        assertFalse(isConfusingNum(1));
        assertTrue(isConfusingNum(6));
        assertFalse(isConfusingNum(8));
        assertTrue(isConfusingNum(9));
        assertFalse(isConfusingNum(11));
        assertTrue(isConfusingNum(10));

    }

    @Test
    public void test0() {
        int N = 20;
        // 6,9,10,16,18,19
        assertEquals(6, confusingNumberII(N));
    }

    @Test
    public void test1() {
        int N = 100;
        // 6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100
        assertEquals(19, confusingNumberII(N));
    }
}
