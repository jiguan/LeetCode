package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQueryImmutable {
	int[] num;
	
	public void setArray(int[] nums) {
		int n = nums.length;
		if(n==0) return;
		this.num = new int[n+1];
		for(int i=0;i<n;i++) {
			num[i+1] = num[i] + nums[i];
		}
	}

	public int sumRange(int i, int j) {
		return num[j+1] - num[i];
	}

	@Test
	public void defaultTest() {
		setArray(new int[]{-2, 0, 3, -5, 2, -1});
		assertEquals(1, sumRange(0, 2));
		assertEquals(-1, sumRange(2, 5));
		assertEquals(-3, sumRange(0, 5));
	}

}
