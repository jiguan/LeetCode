package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 * 
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 * 
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can
 * be 0 and its sum in that case is 0.
 * 
 * As the answer can be very large, return the answer modulo 10^9 + 7
 */
public class KConcatenationMaximumSumByKadensAlgo {
    // Return the maximum sub-array sum in the modified array.
    int mod = (int) Math.pow(10, 9) + 7;

    public int kConcatenationMaxSum(int[] arr, int k) {
        long kadanes = kadanesAlgo(arr);
        if (k == 1) {
            return (int) kadanes;
        }
        long prefixSum = prefixSum(arr);
        long suffixSum = suffixSum(arr);
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }

        if (sum > 0) {
            return (int) (Math.max((sum * (k - 2) % mod + suffixSum % mod + prefixSum % mod) % mod,
                    kadanes % mod));
        } else {
            return (int) (Math.max((prefixSum % mod + suffixSum % mod) % mod, kadanes % mod));
        }

    }

    private long kadanesAlgo(int[] arr) {
        long sum = 0;
        long max = Integer.MIN_VALUE;

        for (int num : arr) {
            sum = sum > 0 ? (sum + num) % mod : num;
            max = Math.max(max, sum);
        }

        return Math.max(max, 0);
    }

    private long prefixSum(int[] arr) {
        long sum = 0;
        long max = Integer.MIN_VALUE;
        for (int num : arr) {
            sum = (sum + num) % mod;
            max = Math.max(max, sum);
        }
        return max;
    }

    private long suffixSum(int[] arr) {
        long sum = 0;
        long max = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; --i) {
            sum = (sum + arr[i]) % mod;
            max = Math.max(max, sum);
        }
        return max;
    }

    @Test
    public void test0() {
        int[] arr = {1, -2, 1};
        int k = 5;
        assertEquals(2, kConcatenationMaxSum(arr, k));
    }
}
