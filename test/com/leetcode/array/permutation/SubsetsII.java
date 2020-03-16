package com.leetcode.array.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();

        backtrack(nums, 0, new LinkedList<Integer>(), res);
        return res;
    }

    private void backtrack(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr));
        for (int i = index; i < nums.length; ++i) {
            if (i > index && nums[i - 1] == nums[i]) continue;

            curr.add(nums[i]);
            backtrack(nums, i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    private void prettyPrint(List<List<Integer>> list) {
        for (List<Integer> l : list) {
            for (Integer i : l) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> result = subsetsWithDup(nums);
        prettyPrint(result);
    }
}
