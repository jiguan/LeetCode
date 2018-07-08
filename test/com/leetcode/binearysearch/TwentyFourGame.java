package com.leetcode.binearysearch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TwentyFourGame {
    Map<Integer, Set<Double>> map = new HashMap<>(16);
    final double eps = 0.001;

    public boolean judgePoint24(int[] nums) {
        List<List<Integer>> permutations = permuteUnique(nums);
        for (List<Integer> permutation : permutations) {
            Set<Double> res = compute(permutation, 0, permutation.size() - 1);
            for (Double r : res) {
                if (Math.abs(r - 24) < eps) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set<Double> compute(List<Integer> nums, int start, int end) {
        int digit = getDigit(nums, start, end);
        if (map.containsKey(digit)) {
            return map.get(digit);
        }

        Set<Double> res = new HashSet<>();
        if (start == end) {
            int val = nums.get(start);
            res.add((double) val);
        } else {
            for (int i = start; i < end; i++) {
                Set<Double> part1 = compute(nums, start, i);
                Set<Double> part2 = compute(nums, i + 1, end);

                for (double num1 : part1) {
                    for (double num2 : part2) {
                        res.add(num1 + num2);
                        res.add(num1 - num2);
                        res.add(num1 * num2);
                        if (num2 != 0) {
                            res.add(num1 / num2);
                        }
                    }
                }
            }
        }
        map.put(digit, res);
        return res;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        traverse(nums, 0, res);
        return res;
    }

    private void traverse(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (visited.add(nums[i])) {
                swap(nums, i, index);
                traverse(nums, index + 1, res);
                swap(nums, i, index);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int getDigit(List<Integer> nums, int start, int end) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            // range: 1 - 9
            int num = nums.get(i);
            res = (res << 4) | num;
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{8, 4, 7, 1};
        assertTrue(judgePoint24(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 9, 1, 2};
        assertTrue(judgePoint24(nums));
    }
    @Test
    public void test2() {
        int[] nums = new int[]{8, 1, 6, 6};
        assertTrue(judgePoint24(nums));
    }

    @Test
    public void test3() {
        int[] nums = new int[]{1, 2, 1, 2};
        assertFalse(judgePoint24(nums));
    }

}
