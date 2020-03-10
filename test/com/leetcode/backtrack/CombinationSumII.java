package com.leetcode.backtrack;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        traverse(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void traverse(int[] cands, int target, int index, List<Integer> cur,
            List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (index == cands.length || target < 0) return;

        for (int i = index; i < cands.length; ++i) {
            // i > index so that we will not miss the case which takes second element into
            // consideration, e.g. 1, 1, 6
            if (i > index && cands[i] == cands[i - 1]) continue;
            cur.add(cands[i]);
            traverse(cands, i + 1, target - cands[i], cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    @Test
    public void test0() {
        int[] candidates = new int[] {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        Set<List<Integer>> expected = new HashSet<>();
        expected.add(Arrays.asList(1, 7));
        expected.add(Arrays.asList(1, 2, 5));
        expected.add(Arrays.asList(2, 6));
        expected.add(Arrays.asList(1, 1, 6));
        assertEquals(expected, new HashSet<>(combinationSum2(candidates, target)));
    }

    @Test
    public void test1() {
        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(1, 2, 3));
        set.add(Arrays.asList(1, 2, 3));
        System.out.println(set.size());
    }
}
