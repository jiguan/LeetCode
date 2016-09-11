package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/*
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.

	For example, given the following triangle
	
	[
	     [2],
	    [3,4],
	   [6,5,7],
	  [4,1,8,3]
	]
	
	The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
 */
public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		int size = triangle.size();
		int[] result = new int[size + 1];

		// start from bottom level
		for (int i = size - 1; i >= 0; i--) {
			List<Integer> list = triangle.get(i);
			for (int j = 0; j < list.size(); j++) {
				result[j] = Math.min(result[j], result[j + 1]) + list.get(j);
			}
		}
		return result[0];
	}

	@Test
	public void test() {
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3, 4));
		triangle.add(Arrays.asList(6, 5, 7));
		triangle.add(Arrays.asList(4, 1, 8, 3));
		assertEquals(11, minimumTotal(triangle));
	}
}
