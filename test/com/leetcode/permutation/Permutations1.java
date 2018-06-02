package com.leetcode.permutation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class Permutations1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // backtrack(nums, 0, new ArrayList<Integer>(), result);
        backtrack1(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
        return res;
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

    private void backtrack1(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == false) {
                current.add(nums[i]);
                visited[i] = true;
                backtrack1(nums, visited, current, result);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
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
