package com.leetcode.array.divide;

public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == target) return true;
            // first part is sorted
            if (nums[mid] > nums[high]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < nums[high]) {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                // since we compare nums[mid] with nums[high]
                high--;
            }
        }
        return false;
    }
}
