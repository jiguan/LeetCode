package com.leetcode.bit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * Find Kth Bit in Nth Binary String
 * 
 * Given two positive integers n and k, the binary string Sn is formed as follows:
 * 
 * S1 = "0" Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1 Where + denotes the concatenation
 * operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0
 * changes to 1 and 1 changes to 0).
 * 
 * For example, the first 4 strings in the above sequence are:
 * 
 * S1 = "0"
 * 
 * S2 = "011"
 * 
 * S3 = "0111001"
 * 
 * S4 = "011100110110001"
 * 
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 */
public class FindKthBitInNthBinaryString {
    // If k is on the left part of the string, we do nothing.
    // If K is right in the middle, we return flip directly.
    // If k is on the right part of the string, find it's symmetric position k = l + 1 -
    // k. Also we toggle flip ^= 1
    public char findKthBit(int n, int k) {
        int flip = 0, l = (1 << n) - 1;
        while (k > 1) {
            if (k == l / 2 + 1)
                return flip == 0 ? '1' : '0';
            if (k > l / 2) {
                k = l + 1 - k;
                flip ^= 1;
            }
            l /= 2;
        }
        return flip == 0 ? '0' : '1';
    }

    @Test
    public void test0() {
        int n = 4, k = 11;
        char expect = '1';
        assertTrue(expect == findKthBit(n, k));
    }
}
