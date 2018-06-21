package com.leetcode.array;

import java.util.Random;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class ShuffleAnArray {
    
    @Test
    public void test0() {
        int[] nums = new int[]{1,2,3};
        Solution soluton = new Solution(nums);
        PrettyPrint.print(soluton.shuffle());
        System.out.println();
        PrettyPrint.print(soluton.reset());
    }
}

class Solution {
    int[] nums;
    Random random = new Random();
    
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;
        int[] res = nums.clone();

        for(int i = nums.length - 1; i>=0; i--) {
            int index = random.nextInt(i + 1);
            swap(res, i, index);
        }
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}