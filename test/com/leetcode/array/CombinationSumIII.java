package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class CombinationSumIII {
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		find(1, n, k, 0, new ArrayList<Integer>(), result);
		return result;
	}
	
	private void find(int start, int n, int k, int add, List<Integer> current, List<List<Integer>> result ) {
		if(add==n) {
			if(current.size()==k) {
				result.add(new ArrayList<>(current));
			}
			return;
		}
		if(add>n||current.size()>=k) {
			return;
		}
		for(int i=start;i<10;i++) {
			current.add(i);
			find(i+1, n, k, add+i, current, result);
			current.remove(current.size()-1);
		}
	}
	
	@Test
	public void test0() {
		int k = 3, n = 9;
		List<List<Integer>> result = combinationSum3(k, n);
		for(List<Integer> list : result) {
			PrettyPrint.print(list);
		}
	}
	
	@Test
	public void test1() {
		int k = 2, n = 18;
		List<List<Integer>> result = combinationSum3(k, n);
		for(List<Integer> list : result) {
			PrettyPrint.print(list);
		}
	}
	
	@Test
	public void test2() {
		int i = -8;
		int j = i>>>3;
		System.out.println(j);
	}
}
