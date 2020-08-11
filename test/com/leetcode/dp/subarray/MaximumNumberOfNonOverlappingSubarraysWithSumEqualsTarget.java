package com.leetcode.dp.subarray;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/*
 * Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
 * 
 * Given an array nums and an integer target.
 * 
 * Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in
 * each subarray is equal to target.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,1,1], target = 2 Output: 2 Explanation: There are 2 non-overlapping
 * subarrays [1,1,1,1,1] with sum equals to target(2). Example 2:
 * 
 * Input: nums = [-1,3,5,1,4,2,-9], target = 6 Output: 2 Explanation: There are 3 subarrays with sum
 * equal to 6. ([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping. Example 3:
 * 
 * Input: nums = [-2,6,6,3,5,4,1,2,8], target = 10 Output: 3 Example 4:
 * 
 * Input: nums = [0,0,0], target = 0 Output: 3
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {
    public int maxNonOverlapping(int[] nums, int target) {
        // sum - how many
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0, res = 0;
        for (int num : nums) {
            sum += num;
            // s1 - s0 = target, a new subarray found
            if (map.containsKey(sum - target)) {
                res = Math.max(res, map.get(sum - target) + 1);
            }
            map.put(sum, res);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {-2, 6, 6, 3, 5, 4, 1, 2, 8};
        int target = 10;
        assertEquals(3, maxNonOverlapping(nums, target));
    }

    @Test
    public void test1() {
        int[] nums = {-1, 3, 5, 1, 4, 2, -9};
        int target = 6;
        assertEquals(3, maxNonOverlapping(nums, target));
    }
}
