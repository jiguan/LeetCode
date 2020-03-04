package com.leetcode.permutation;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class Subsets {
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
        int[] nums = new int[] {1, 2, 3};
        Set<List<Integer>> result = new HashSet<>(subsets(nums));
        Set<List<Integer>> expected = new HashSet<>(Arrays.asList(Arrays.asList(3),
                Arrays.asList(1), Arrays.asList(2), Arrays.asList(1, 2, 3), Arrays.asList(1, 3),
                Arrays.asList(2, 3), Arrays.asList(1, 2), new ArrayList<>()));
        assertEquals(expected, result);
    }

}
