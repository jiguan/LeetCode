package com.leetcode.array.divide;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        return findMax(nums, 0, nums.length - 1);
    }

    // T(n) = 2*T(n/2) + O(n) = жи(nlogn)
    // https://math.dartmouth.edu/archive/m19w03/public_html/Section5-2.pdf
    private int findMax(int[] nums, int low, int high) {
        if (low == high) return nums[low];
        int mid = (high - low) / 2 + low;

        int leftSum = findMax(nums, low, mid);
        int rightSum = findMax(nums, mid + 1, high);
        int crossSum = cross(nums, low, high);
        return Math.max(crossSum, Math.max(leftSum, rightSum));
    }

    private int cross(int[] nums, int left, int right) {
        int leftSum = 0;
        int rightSum = 0;
        int sum = 0;
        int mid = (right - left) / 2 + left;
        for (int i = mid - 1; i >= left; --i) {
            // consecutive sum
            sum = sum + nums[i];
            // record the max
            leftSum = Math.max(leftSum, sum);
        }
        sum = 0;
        for (int i = mid + 1; i <= right; ++i) {
            sum = sum + nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        return nums[mid] + leftSum + rightSum;
    }

    @Test
    public void test0() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected = 6;
        assertEquals(expected, maxSubArray(nums));
    }
}
