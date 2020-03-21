package com.leetcode.array.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || k == 0 || n < k) return result;
        traverse(n, k, 1, new ArrayList<Integer>(), result);
        return result;
    }

    private void traverse(int n, int k, int start, List<Integer> curr, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<Integer>(curr));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            curr.add(i);
            traverse(n, k - 1, i + 1, curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}
