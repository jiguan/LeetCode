package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        traverse(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void traverse(int[] candidates, int target, int start,
            List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(cur));
        } else {
            for (int i = start; i < candidates.length
                    && target >= candidates[i]; ++i) {
                cur.add(candidates[i]);
                traverse(candidates, target - candidates[i], i, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum0(int[] candidates, int target) {
        List<List<List<Integer>>> result = new ArrayList<>();
        Arrays.sort(candidates);
        for (int i = 1; i <= target; i++) {
            List<List<Integer>> newList = new ArrayList<>();
            for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
                if (i == candidates[j])
                    newList.add(Arrays.asList(i));
                else {
                    for (List<Integer> list : result
                            .get(i - candidates[j] - 1)) {
                        if (candidates[j] <= list.get(0)) {
                            List<Integer> tmp = new ArrayList<>();
                            tmp.add(candidates[j]);
                            tmp.addAll(list);
                            newList.add(tmp);
                        }
                    }
                }
            }
            result.add(newList);
        }
        return result.get(target - 1);
    }

    @Test
    public void test0() {
        int[] candidates = new int[]{2, 3, 6, 7};
        List<List<Integer>> expected = combinationSum(candidates, 7);
        PrettyPrint.print(expected);
    }

    @Test
    public void test1() {
        int[] candidates = new int[]{1, 2, 3, 4};
        List<List<Integer>> expected = combinationSum(candidates, 4);
        PrettyPrint.print(expected);
    }

    @Test
    public void test2() {
        int[] candidates = new int[]{2};
        List<List<Integer>> expected = combinationSum(candidates, 1);
        PrettyPrint.print(expected);
    }

}
