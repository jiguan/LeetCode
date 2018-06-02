package com.leetcode.bit;

public class BitwiseAndOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0; // i means we have how many bits are 0 on the right
        // after removing the different part, m == n
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}
