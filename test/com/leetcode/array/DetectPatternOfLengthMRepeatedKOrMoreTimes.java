package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * Detect Pattern of Length M Repeated K or More Times
 * 
 * Given an array of positive integers arr, find a pattern of length m that is repeated k or more
 * times.
 * 
 * A pattern is a subarray (consecutive sub-sequence) that consists of one or more values, repeated
 * multiple times consecutively without overlapping. A pattern is defined by its length and the
 * number of repetitions.
 * 
 * Return true if there exists a pattern of length m that is repeated k or more times, otherwise
 * return false
 */
public class DetectPatternOfLengthMRepeatedKOrMoreTimes {
    public boolean containsPattern(int[] arr, int m, int k) {
        int curr = 0;
        for (int i = 0; i + m < arr.length; ++i) {
            if (arr[i] != arr[i + m]) {
                curr = 0;
            } else {
                curr++;
            }

            // since we compare i with i + m, curr is for the (k - 1) match
            if (curr == (k - 1) * m) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test0() {
        int[] arr = {1, 2, 4, 4, 4, 4};
        int m = 1, k = 4;

        // The pattern (4) of length 1 is repeated 4 consecutive times. Notice that pattern can be
        // repeated k or more times but not less.
        assertTrue(containsPattern(arr, m, k));
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 1, 2, 3, 1, 1, 1, 3};
        int m = 3, k = 2;

        // The pattern (1,2) of length 2 is repeated 2 consecutive times. Another valid pattern
        // (2,1) is also repeated 2 times.
        assertTrue(containsPattern(arr, m, k));
    }

    @Test
    public void test3() {
        int[] arr = {1, 2, 1, 2, 1, 3};
        int m = 2, k = 3;
        assertFalse(containsPattern(arr, m, k));
    }

}
