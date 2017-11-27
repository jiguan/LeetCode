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
        if (nums.length < 2)
            return true;

        for (int i = nums.length - 2; i >= 0; --i) {
            // Only need to consider '0' situation
            if (nums[i] == 0) {
                int jump = 1;
                while (jump > nums[i]) {
                    ++jump;
                    --i;
                    if (i < 0)
                        return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{9, 4, 2, 1, 0, 2, 0};
        assertTrue(canJump(nums));
    }
}
