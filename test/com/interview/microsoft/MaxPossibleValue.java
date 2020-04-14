package com.interview.microsoft;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// https://leetcode.com/discuss/interview-question/398050/3
/*
 * Write a function that, given an integer N, returns the maximum possible value obtained by
 * inserting one '5' digits inside teh decimal representation of integer N. Examples: Give N = 268,
 * the function should return 5268; N = -999, the function should return -5999
 */
public class MaxPossibleValue {
    public int solutation(int N) {
        StringBuilder sb = new StringBuilder(String.valueOf(N));
        int index = 0;

        if (N >= 0) {
            while (index < sb.length() && sb.charAt(index) - '0' >= 5) {
                index++;
            }
        } else {
            index = 1;
            while (index >=0 && (sb.charAt(index) - '0') <= 5) {
                index--;
            }
        }
        sb.insert(index, 5);
        return Integer.valueOf(sb.toString());
    }

    @Test
    public void test0() {
        int N = -999;
        assertEquals(-5999, solutation(N));
    }

    @Test
    public void test1() {
        int N = 268;
        assertEquals(5268, solutation(N));
    }
}
