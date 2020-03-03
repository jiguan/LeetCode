package com.leetcode.greedy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*
 * https://leetcode.com/problems/jump-game/solution/
 */

public class JumpGame {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            if (i + nums[i] >= lastPos) {
                // final position can be reached
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public boolean canJump0(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) return false;
            reach = Math.max(reach, i);
        }
        return true;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{9, 4, 2, 1, 0, 2, 0};
        assertTrue(canJump(nums));
    }
}
