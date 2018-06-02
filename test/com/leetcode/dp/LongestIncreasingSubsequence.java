package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            if (dp[len] < nums[i]) {
                dp[++len] = nums[i];
            } else {
                int index = search(dp, len, nums[i]);
                dp[index] = nums[i];
            }
        }

        return len + 1;
    }

    private int search(int[] nums, int len, int val) {
        int start = 0, end = len;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > val) {
                // mid could be the index which is about to be replaced
                end = mid;
            } else if (nums[mid] < val) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    // https://www.youtube.com/watch?v=CE2b_-XfVDk
    public int lengthOfLIS0(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    res = Math.max(res, dp[i]);
                }
            }
        }

        return res;
    }

    @Test
    public void testSearch() {
        int[] nums = new int[]{2, 5, 6};
        int val = 4, len = 2;
        assertEquals(Integer.valueOf(1), Integer.valueOf(search(nums, len, val)));
    }

    @Test
    public void test0() {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        assertEquals(4, lengthOfLIS(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{10, 9, 2, 5, 3, 4};
        assertEquals(3, lengthOfLIS(nums));
    }

    @Test
    public void test2() {
        // 2, 4, 5, 6, 7, 12
        int[] nums = new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        assertEquals(6, lengthOfLIS(nums));
    }
}
