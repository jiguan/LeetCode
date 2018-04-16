package com.leetcode.permutation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class Permutations1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        backtrack(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = 0; i <= current.size(); i++) {
            current.add(i, nums[index]);
            backtrack(nums, index + 1, current, result);
            current.remove(i);
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = permute(nums);
        for (List<Integer> list : result) {
            PrettyPrint.print(list);
        }
    }

}
