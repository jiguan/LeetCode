package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        find(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void find(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target > 0) {
            for(int i=start;i<candidates.length&&target-candidates[i]>=0;i++) {
                if(i>start&&candidates[i-1]==candidates[i]) continue;
                current.add(candidates[i]);
                find(candidates, target - candidates[i], i+1, current, result);
                current.remove(current.size() - 1);
            }
         
        } else if (target == 0) {
            result.add(new ArrayList<>(current));
        }
    }

    private void prettyPrint(List<List<Integer>> lists) {
        if (lists == null)
            System.out.println("null");
        for (List<Integer> list : lists) {
            System.out.print("[");
            for (int num : list) {
                System.out.print(num + ", ");
            }
            System.out.println("]");
        }
    }

    @Test
    public void test0() {
        int[] candidates = new int[] {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        prettyPrint(combinationSum2(candidates, target));
    }
}
