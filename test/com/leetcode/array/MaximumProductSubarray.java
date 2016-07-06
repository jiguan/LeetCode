package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		int ans = nums[0], min = ans, max = ans;
		for(int i=1;i<nums.length;i++) {
			int num = nums[i];
			if(num>0) {
				max = Math.max(num, num * max);
				min = Math.min(num, num * min);
			} else {
				int tmp = max;
				max = Math.max(num, min * num);
				min = Math.min(num, num * tmp);
			}
			ans = Math.max(ans, max);
		}
		return ans;
	}
		
	
	@Test
	public void test0() {
		int[] nums = new int[]{2,3,-3,4};
		assertEquals(6, maxProduct(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{2,3,0,-3,2};
		assertEquals(6, maxProduct(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{-2,-5,0,-3,2,2,-1};
		assertEquals(12, maxProduct(nums));
	}
	
	@Test
	public void test3() {
		int[] nums = new int[]{-2};
		assertEquals(-2, maxProduct(nums));
	}
	
	@Test
	public void test4() {
		int[] nums = new int[]{0};
		assertEquals(0, maxProduct(nums));
	}
	
	@Test
	public void test5() {
		int[] nums = new int[]{3,0};
		assertEquals(3, maxProduct(nums));
	}
	
	@Test
	public void test6() {
		int[] nums = new int[]{0, 0, 0};
		assertEquals(0, maxProduct(nums));
	}
	
	@Test
	public void test7() {
		int[] nums = new int[]{-1,-2,-3,0};
		assertEquals(6, maxProduct(nums));
	}
	
}
