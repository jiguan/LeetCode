package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DiagonalTraverseII {
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
		List<List<Integer>> res = new ArrayList<>();
		int totalNum = 0;

		for (int i = 0; i < nums.size(); ++i) {
			for (int j = 0; j < nums.get(i).size(); ++j) {
				if (i + j == res.size()) {
					res.add(new ArrayList<>());
				}
				res.get(i + j).add(0, nums.get(i).get(j));
				totalNum++;
			}
		}

		int[] tmp = new int[totalNum];
		int i = 0;
		for (List<Integer> list : res) {
			for (int num : list) {
				tmp[i++] = num;
			}
		}

		return tmp;
	}

	@Test
	public void test0() {
		List<List<Integer>> nums = new ArrayList<>();
		nums.add(Arrays.asList(1, 2, 3, 4, 5));
		nums.add(Arrays.asList(6, 7));
		nums.add(Arrays.asList(8));
		nums.add(Arrays.asList(9, 10, 11));
		nums.add(Arrays.asList(12, 13, 14, 15, 16));

		int[] expected = { 1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16 };
		assertArrayEquals(expected, findDiagonalOrder(nums));
	}
}
