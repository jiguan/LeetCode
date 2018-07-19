package com.leetcode.permutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class Permutations {
    // 7ms
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i <= curr.size(); ++i) {
            curr.add(i, nums[index]);
            backtrack(nums, index + 1, curr, res);
            curr.remove(i);
        }
    }
    // 3ms
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;

        List<Integer> tmp = new LinkedList<>();
        tmp.add(nums[0]);
        res.add(tmp);
        // i - insert index
        for (int i = 1; i < nums.length; ++i) {
            List<List<Integer>> newRes = new LinkedList<>();
            // j - insert position
            for (int j = 0; j <= i; ++j) {
                for (List<Integer> list : res) {
                    List<Integer> newTmp = new LinkedList<>(list);
                    newTmp.add(j, nums[i]);
                    newRes.add(newTmp);
                }
            }
            res = newRes;
        }
        return res;
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
