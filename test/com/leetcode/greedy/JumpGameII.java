package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// Each element in the array represents your maximum jump length at that position.
// Your goal is to reach the last index in the minimum number of jumps.
public class JumpGameII {
    public int jump(int[] nums) {
        int steps = 0;
        int end = 0;
        int jump = 0; // max range it can reach

        for (int i = 0; i < nums.length; i++) {
            // These 2 steps combine jump1 and jump2, it checks whether the last one is reachable,
            // rather than stop and return
            if (i > jump) return -1;
            if (i == nums.length - 1) return steps;
            jump = Math.max(jump, nums[i] + i);
            if (i == end) {
                steps++;
                end = jump;
            }
        }
        return steps;
    }

    @Test
    public void test0() {
        int[] nums = {2, 3, 1, 1, 4};
        int expected = 2;
        assertEquals(expected, jump(nums));
    }

    @Test
    public void test1() {
        int[] nums = {3, 2, 1, 0, 4};
        int expected = -1;
        assertEquals(expected, jump(nums));
    }

}
