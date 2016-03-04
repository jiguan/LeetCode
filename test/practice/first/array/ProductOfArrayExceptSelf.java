package practice.first.array;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/*
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
	Solve it without division and in O(n).
	For example, given [1,2,3,4], return [24,12,8,6].
	Follow up:
	Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int[] outputs = new int[nums.length];
		int left = 1;
		for(int i = 0;i<nums.length;i++) {
			left *= nums[i];
			outputs[i] = left;
		}
		int right = 1;
		for(int i=nums.length-1;i>0;i--) {
			outputs[i] = right * outputs[i-1];
			right *= nums[i];
		}
		outputs[0] = right;
		return outputs;
	}
	
	private void print(int[] arr) {
		for(int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	@Test
	public void test0() {
		int[] arr = new int[]{1,2,3,4};
		int[] result = productExceptSelf(arr);
		print(result);
	}
	
	@Test
	public void test1() {
		int[] arr = new int[]{4,3,2,1,2};
		int[] expected = new int[]{12,16,24,48,24};
		int[] actual = productExceptSelf(arr);
		assertTrue(Arrays.equals(expected, actual));
	}
}
