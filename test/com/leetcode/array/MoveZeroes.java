package com.leetcode.array;

import org.junit.Test;

public class MoveZeroes {
	public void moveZeroes1(int[] nums) {
		int j =0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=0) {
				nums[j++] =  nums[i];
			}
		}
		while(j<nums.length) {
			nums[j++] = 0;
		}
	}
	
	public void moveZeroes(int[] nums) {
		for(int i=0,j=0;i<nums.length;i++) {
			if(nums[i]!=0) {
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
				j++;
			}
		}
		
	}
	
	
	@Test
	public void test1() {
		int[] nums = new int[]{0,0,1,0,0};
		moveZeroes(nums);
		printNums(nums);
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{1,2,1,4,5};
		moveZeroes(nums);
		printNums(nums);
	}
	
	@Test
	public void test3() {
		int[] nums = new int[]{0};
		moveZeroes(nums);
		printNums(nums);
	}
	
	@Test
	public void test4() {
		int[] nums = new int[]{1,0,3,0,5};
		moveZeroes(nums);
		printNums(nums);
	}
	
	private void printNums(int[] nums) {
		for(int n : nums) {
			System.out.print(n+",");
		}
		System.out.println();
	}
}
