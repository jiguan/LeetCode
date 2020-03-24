package com.leetcode.array.divide;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int midVal = nums[mid];
            if (midVal == target) return mid;
            // first part is sorted
            if (midVal > nums[high]) {
                if (nums[low] <= target && target < midVal) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            } else {
                if (midVal < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
