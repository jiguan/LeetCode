package practice.first.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MissingNumber {
	public int missingNumber(int[] nums) {
		int tmp = 0;
		for(int i=1;i<=nums.length;i++) {
			tmp = nums[i-1] ^ i ^ tmp; 
		}
		return tmp;
	}

	@Test
	public void test0() {
		int[] nums = new int[]{0,2,3,4,5,6};
		assertEquals(1, missingNumber(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{0,1,2,3,4,6};
		assertEquals(5, missingNumber(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{1,2,3,4,5,6};
		assertEquals(0, missingNumber(nums));
	}
	
	@Test
	public void test3() {
		System.out.println(0 ^ 4);
	}
	
	
}
