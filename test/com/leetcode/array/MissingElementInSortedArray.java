package com.leetcode.array;

/*
 * Missing Element in Sorted Array
 * 
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost
 * number of the array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: A = [4,7,9,10], K = 1 Output: 5 Explanation: The first missing number is 5. Example 2:
 * 
 * Input: A = [4,7,9,10], K = 3 Output: 8 Explanation: The missing numbers are [5,6,8,...], hence
 * the third missing number is 8. Example 3:
 * 
 * Input: A = [1,2,4], K = 3 Output: 6 Explanation: The missing numbers are [3,5,6,7,...], hence the
 * third missing number is 6.
 * 
 */
public class MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int missing = nums[right] - nums[left] - (right - left);

        if (k > missing) {
            return nums[right] + k - missing;
        }

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            missing = nums[mid] - nums[left] - (mid - left);

            if (k > missing) {
                k -= missing;
                left = mid;
            } else {
                right = mid;
            }
        }

        return nums[left] + k;
    }
}
