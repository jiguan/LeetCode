package com.leetcode.array;

public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        long product = 1;
        int res = 0;
        int left = 0;
        for (int right = 0; right < nums.length; ++right) {
            product *= nums[right];
            // <= is needed since there could be no valid element
            // e.g. 1,2,3 0 right - left + 1 => 0
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            res += (right - left + 1);
        }
        return res;
    }
}
