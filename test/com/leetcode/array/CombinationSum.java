package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        find(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void find(int[] candidates, int target,  int start, List<Integer> current, List<List<Integer>> result) {
        if(target>0) {
            for(int i=start;i<candidates.length&&candidates[i]<=target;i++) {
                current.add(candidates[i]);
                find(candidates, target-candidates[i], i, current, result);
                current.remove(current.size()-1);
            }
            
        } else if(target==0) {
            result.add(new ArrayList<>(current));
        }
    }
    
    
    public List<List<Integer>> combinationSum0(int[] candidates, int target) {
        List<List<List<Integer>>> result = new ArrayList<>();
        Arrays.sort(candidates);
        for(int i=1;i<=target;i++) {
            List<List<Integer>> newList = new ArrayList<>();
            for(int j=0;j<candidates.length&&candidates[j]<=i;j++) {
                if(i==candidates[j]) newList.add(Arrays.asList(i));
                else {
                    for(List<Integer> list : result.get(i-candidates[j]-1)) {
                        if(candidates[j]<=list.get(0)) {
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
        return result.get(target-1);
    }
    
    private void prettyPrint(List<List<Integer>> lists) {
        if(lists==null) System.out.println("null");
        for(List<Integer> list : lists) {
            System.out.print("[");
            for(int num : list) {
                System.out.print(num+", ");
            }
            System.out.println("]");
        }
    }
    
    @Test
    public void test0() {
        int[] candidates = new int[]{2,3,6,7};
        List<List<Integer>> expected = combinationSum(candidates, 7);
        prettyPrint(expected);  
    }
    
    @Test
    public void test1() {
        int[] candidates = new int[]{1,2,3,4};
        List<List<Integer>> expected = combinationSum(candidates, 4);
        prettyPrint(expected);  
    }
    @Test
    public void test2() {
        int[] candidates = new int[]{2};
        List<List<Integer>> expected = combinationSum(candidates, 1);
        prettyPrint(expected);  
    }
    
}
