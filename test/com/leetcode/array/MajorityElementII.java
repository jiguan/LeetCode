package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MajorityElementII {
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> result = new ArrayList<>();
		int[] cand1 = new int[2], cand2 = new int[2];
		for(int i = 0;i<nums.length;i++) {
			int num = nums[i];
			if(cand1[1]==0&&cand2[0]!=num) {
				cand1[0] = num;
				cand1[1] = 1;
			} else if(cand1[0]==num) {
				cand1[1]++;
			} else if (cand2[1]==0) {
				cand2[0] = num;
				cand2[1] = 1;
			}  else if(cand2[0]==num) {
				cand2[1]++;
			} else {
				cand1[1] = Math.max(0, cand1[1]-1);
				cand2[1] = Math.max(0, cand2[1]-1);
			}
		}
		
		int time1 = 0, time2 = 0;
		for(int num :nums) {
			if(num==cand1[0]) {
				time1++;
			}
			else if (num==cand2[0]) {
				time2++;
			}
		}
		if(time1>(nums.length/3)) result.add(cand1[0]);
		if(time2>(nums.length/3)) result.add(cand2[0]);
 		return result;
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{2,2,2,2,1,1,3,3,3};
		List<Integer> expected = Arrays.asList(2);
		assertEquals(expected, majorityElement(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{1};
		List<Integer> expected = Arrays.asList(1);
		assertEquals(expected, majorityElement(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{1,2,2,3,2,1,1,3};
		List<Integer> expected = Arrays.asList(1, 2);
		assertEquals(expected, majorityElement(nums));
	}
	
	
}
