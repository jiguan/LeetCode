package com.leetcode.permutation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == false) {
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                    continue;
                }
                current.add(nums[i]);
                visited[i] = true;
                backtrack(nums, visited, current, result);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }

    }

    public List<List<Integer>> permuteUnique0(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        traverse(nums, 0, res);
        return res;
    }

    private void traverse(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> list = new LinkedList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = index; i < nums.length; ++i) {
            if (visited.add(nums[i])) {
                swap(nums, i, index);
                traverse(nums, i + 1, res);
                swap(nums, i, index);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 1, 3};
        List<List<Integer>> result = permuteUnique0(nums);
        assertEquals(Integer.valueOf(3), Integer.valueOf(result.size()));
        for (List<Integer> list : result) {
            PrettyPrint.print(list);
        }
    }
}
