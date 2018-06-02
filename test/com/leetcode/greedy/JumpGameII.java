package com.leetcode.greedy;

// Each element in the array represents your maximum jump length at that position.
// Your goal is to reach the last index in the minimum number of jumps.
public class JumpGameII {
    public int jump(int[] nums) {
        int steps = 0;
        int end = 0;
        int jump = 0; // max range it can reach
        
        for(int i = 0 ; i < nums.length - 1; i++) {
            jump = Math.max(jump, nums[i] + i);
            if(i == end) {
                steps++;
                end = jump;
            }
        }
        return steps;
    }

}
