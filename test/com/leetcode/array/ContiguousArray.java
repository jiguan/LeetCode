package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        // zero count - index
        Map<Integer, Integer> map = new HashMap<>();
        int zero = 0;
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                zero++;
            } else {
                zero--;
            }
            // we have equal number of ones and zeros
            // since we count '0' from index=0, length = i + 1
            if (zero == 0) {
                res = i + 1;
            } else if (!map.containsKey(zero)) {
                map.put(zero, i);
            } else {
                // if at any index i we have zero = 1, then we have 1 diff (1 more 0s than 1s), we
                // record it in the HashMap, and if later at index j we meet another situation that
                // zero = 1, this means from the index i till the index j, the number of 0s and 1s
                // are the same.
                res = Math.max(res, i - map.get(zero));
            }
        }
        return res;

    }

    @Test
    public void test0() {
        int[] nums = {0, 1, 1, 1, 0};
        int expected = 2;
        assertEquals(expected, findMaxLength(nums));
    }

    @Test
    public void test1() {
        int[] nums = {0, 1, 1, 1, 0, 1, 0, 0};
        int expected = 8;
        assertEquals(expected, findMaxLength(nums));
    }
}
