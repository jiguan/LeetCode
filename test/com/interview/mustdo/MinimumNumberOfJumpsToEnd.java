package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
/*
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. 
 * Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
 * If an element is 0, then cannot move through that element.
 *
 * Example:
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 8 ->9)
 * First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg. to 5 or 8 or 9.
 */
public class MinimumNumberOfJumpsToEnd {
	public int move(int[] arr) {
		int len = arr.length;
		int[] step = new int[len];
		for(int i=1;i<step.length;i++) {
			step[i] = Integer.MAX_VALUE-1;
		}
		
		for(int i=0;i<len;i++) {
			int element = arr[i];
			for(int j=1;j<=element&&(j+i)<len;j++) {
				step[j+i] = Math.min(step[j+i], step[i]+1);
			}
		}
		prettyPrint(arr);
		prettyPrint(step);
		
		return step[len-1];
	}
	
	
	private void prettyPrint(int[] nums) {
		for(int num : nums) {
			System.out.print(num+" ");
		}
		System.out.println();
	}
	
	@Test
	public void test0() {
		int[] arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		assertEquals(3, move(arr));
	}
	
	
}
