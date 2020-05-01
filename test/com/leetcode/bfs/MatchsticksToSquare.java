package com.leetcode.bfs;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        quickSort(nums, 0, nums.length -1);
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 4 != 0) return false;
        return dfs(nums, 0, sum / 4, new int[4]);
    }
    
    private boolean dfs(int[] nums, int index, int target, int[] res) {
        if(index == nums.length) {
            if(res[0] == target && res[1] == target && res[2] == target) {
                return true;
            }    
            return false;
        }

        for(int i= 0 ; i < res.length; i++) {
            if(res[i] + nums[index] > target) continue;
            res[i] += nums[index];
            if(dfs(nums, index + 1, target, res)) {
                return true;
            }
            res[i] -= nums[index];
        }
        
        return false;
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if(start < end) {
            int p = partition(nums, start, end);
            quickSort(nums, start, p-1);
            quickSort(nums, p+1, end);
        }
    }
    
    private int partition(int[] nums, int start, int end) {
        int i = start, pivot = nums[end];
        for(int j = start; j <end; j++) {
            if(nums[j] >= pivot) {
                swap(nums, i , j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        if(nums[i] == nums[j]) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[j] ^ nums[i];
        nums[i] = nums[i] ^ nums[j];
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 1, 2, 2, 2};
        assertTrue(makesquare(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{12, 18, 2, 2, 16, 8, 7, 3, 10, 12, 3, 20, 2, 10, 19};
        assertTrue(makesquare(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{2, 2, 2, 3, 4, 4, 4, 5, 6};
        assertTrue(makesquare(nums));
    }
}
