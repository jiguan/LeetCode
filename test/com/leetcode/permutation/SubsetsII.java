package com.leetcode.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<Integer>());
		if (nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);
		int begin = 0;
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			int size = res.size();
			if (i == 0 || nums[i] != nums[i - 1])
				begin = 0;
			for (int j = begin; j < size; j++) {
				List<Integer> ans = new ArrayList<Integer>(res.get(j));
				ans.add(num);
				res.add(ans);
			}
			begin = size;
		}
		return res;
	}

	private void prettyPrint(List<List<Integer>> list) {
		for (List<Integer> l : list) {
			for (Integer i : l) {
				System.out.print(i);
			}
			System.out.println();
		}
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 2, 2 };
		List<List<Integer>> result = subsetsWithDup(nums);
		prettyPrint(result);
	}
}
