package com.leetcode.permutation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Subsets {

    public List<List<Integer>> subsets1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());

        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> tmp = new ArrayList<>(result.get(i));
                tmp.add(num);
                result.add(tmp);
            }
        }
        return result;
    }

    public List<List<Integer>> subsets0(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        return fill(0, nums);
    }

    private List<List<Integer>> fill(int index, int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        if (index >= nums.length) return result;
        for (int i = index; i < nums.length; i++) {
            int element = nums[i];
            List<List<Integer>> returned = fill(i + 1, nums);
            for (List<Integer> list : returned) {
                list.add(0, element);
                result.add(list);
            }
        }
        return result;
    }

    private void prettyPrint(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            for (Integer ele : list) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        traverse(nums, 0, new LinkedList<Integer>(), res);
        return res;
    }

    private void traverse(int[] nums, int start, List<Integer> cur, List<List<Integer>> res) {
        res.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; ++i) {
            cur.add(nums[i]);
            traverse(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = subsets(nums);
        prettyPrint(result);
    }

}
