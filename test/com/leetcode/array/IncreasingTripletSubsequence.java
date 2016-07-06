package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IncreasingTripletSubsequence {
	public boolean increasingTriplet(int[] nums) {
		if(nums==null || nums.length<=1) return false;
		Integer A = nums[0], B = null;
		for(int i =1;i<nums.length;i++) {
			if(B!=null && nums[i] > B) {
				return true;
			}
			if(nums[i] <= A) {
				A = nums[i];
			} else if(B==null || nums[i] < B) {
				B = nums[i];
			}
		}
		return false;
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{1,2,3};
		assertTrue(increasingTriplet(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{10,9,11,8,12};
		assertTrue(increasingTriplet(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{10,9,11,8,10};
		assertFalse(increasingTriplet(nums));
	}
}
