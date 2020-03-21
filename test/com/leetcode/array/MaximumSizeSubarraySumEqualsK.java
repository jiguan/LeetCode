package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, res = 0;
        // sum - index: to get sum, what is the ending index
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            // we want to save the furthest index
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            
            if (sum == k) {
                res = i + 1;
            } else if (map.containsKey(sum - k)) {
                // find a subarray, (sum - k) is the starting index, sum = current index, length =
                // map.get(sum) - map.get(sum - k)
                res = Math.max(res, i - map.get(sum - k));
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        assertEquals(4, maxSubArrayLen(nums, k));
    }
}
