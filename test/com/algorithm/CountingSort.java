package com.algorithm;

import org.junit.Test;

public class CountingSort {
	int[] count; //element - times
	
	public int[] sort(int[] nums) {
		int max = nums[0];
		for(int num : nums) {
			if(num>max) max = num;
		}
		count = new int[max+1];
		
		for(int num : nums) {
			count[num] += 1;
		}
		int total = 0;
		for(int i=0;i<=max;i++) {
			int oldCount = count[i];
			count[i] = total;
			total += oldCount;
		}
		int[] output = new int[nums.length];
		for(int num : nums) {
			output[count[num]] = num;
			count[num] += 1; //move to next positime
		}
		return output;
	}
	
	@Test
	public void test() {
		int[] nums = new int[]{8,4,2,0,0,6,7};
		print(sort(nums));
	}
	
	public void print(int [] nums) {
		for(int num : nums) {
			System.out.print(num+" ");
		}
		System.out.println();
	}

}
