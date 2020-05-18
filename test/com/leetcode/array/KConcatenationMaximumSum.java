package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class KConcatenationMaximumSum {
    int m = 1000000007;

    /*
     * 1. maximum sum is located inside the array without any concatenation (this is refer to 53.
     * Maximum Subarray)
     * 
     * 2. if sum of array is negative - maximum sum is over somewhere after two array concatenation
     * 
     * 3. if sum of the array is positive - maximum sum is based on case 2 + (k - 2) * sum
     */
    public int kConcatenationMaxSum(int[] a, int k) {
        int n = a.length;
        int max = a[0], sum = a[0];
        // Get max sum
        for (int i = 1; i < n; i++) {
            sum = Math.max(sum + a[i], a[i]);
            max = Math.max(max, sum);
        }
        if (k < 2) return max;
        int leftSum = a[0], leftMax = Math.max(0, a[0]);
        int rightSum = a[n - 1], rightMax = Math.max(0, a[n - 1]);
        for (int i = 1; i < n; i++) {
            // Different from sum
            leftSum += a[i];
            leftMax = Math.max(leftMax, leftSum);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightSum += a[i];
            rightMax = Math.max(rightMax, rightSum);
        }
        // prefix + suffix
        int tmp = rightMax + leftMax;
        // whether we should sum over (k-2) arrays
        if (leftSum < 0) {
            return Math.max(max, tmp);
        } else {
            return Math.max(max, (int) (tmp + ((k - 2) * (long) leftSum) % m));
        }
    }


    @Test
    public void test0() {
        int[] arr = {1, -2, 1};
        int k = 5;
        assertEquals(2, kConcatenationMaxSum(arr, k));
    }
}
