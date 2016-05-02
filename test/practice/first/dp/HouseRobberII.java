package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobberII {
	public int rob(int[] nums) {
		if(nums==null||nums.length==0) return 0;
		int[] zero = new int[nums.length];
		zero[1] = nums[0];
		int[] one = new int[nums.length];
		one[1] = nums[1];
		for(int i=2;i<nums.length;i++) {
			zero[i] = Math.max(zero[i-2]+nums[i-1], zero[i-1]);
			one[i] = Math.max(one[i-2]+nums[i], one[i-1]);
		}
		return Math.max(zero[nums.length-1], one[nums.length-1]);
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{1,2,3,4,5};
		assertEquals(8, rob(nums));
	}
}
