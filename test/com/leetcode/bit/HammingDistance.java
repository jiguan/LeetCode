package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int res = 0;
        while (xor != 0) {
            res += (xor & 1);
            xor >>= 1;
        }
        return res;
    }

    @Test
    public void test0() {
        int x = 1, y = 4;
        assertEquals(2, hammingDistance(x, y));
    }
}
