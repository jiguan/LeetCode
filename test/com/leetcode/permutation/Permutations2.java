package com.leetcode.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class Permutations2 {
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> results = new ArrayList<>();
    	results.add(Arrays.asList(nums[0]));
    	// i is the index of element currently moving
    	for(int i=1;i<nums.length;++i) {
    		int resultsNum=results.size();
			// Repeat j times, remove the every existing results
			for(int j=0;j<resultsNum;++j) {
				List<Integer> result = results.remove(0);
	    		// k is the index where we are moving
	    		for(int k=0;k<=i;++k) {
    				List<Integer> tmp = new ArrayList<>(result);
    				tmp.add(k, nums[i]);
    				results.add(tmp);
    			}
    		}
    	}
    	return results;
    }
    @Test
    public void test0() {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = permute(nums);
        for(List<Integer> list : result) {
            PrettyPrint.print(list);
        }
    }
    
}
