package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        helper(k, n, 1, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int k, int n, int start, int sum, List<Integer> current, List<List<Integer>> res) {
        if (current.size() >= k || sum >= n) {
            if (current.size() == k && sum == n) {
                res.add(new ArrayList<>(current));
            }
            return;
        }

        for (int i = start; i < 10; ++i) {
            current.add(i);
            helper(k, n, i + 1, sum + i, current, res);
            current.remove(current.size() - 1);
        }
    }

    @Test
    public void test0() {
        int k = 3, n = 15;
        Set<List<Integer>> actual = new HashSet<>(combinationSum3(k, n));
        Set<List<Integer>> expected = new HashSet<>();
        expected.add(Arrays.asList(1, 5, 9));
        expected.add(Arrays.asList(1, 6, 8));
        expected.add(Arrays.asList(2, 4, 9));
        expected.add(Arrays.asList(2, 5, 8));
        expected.add(Arrays.asList(2, 6, 7));
        expected.add(Arrays.asList(3, 4, 8));
        expected.add(Arrays.asList(3, 5, 7));
        expected.add(Arrays.asList(4, 5, 6));

        assertEquals(expected, actual);
    }

}
