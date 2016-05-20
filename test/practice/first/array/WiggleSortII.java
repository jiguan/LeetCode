package practice.first.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import practice.first.util.PrettyPrint;

public class WiggleSortII {
	public void wiggleSort(int[] nums) {
		Arrays.sort(nums);
		for(int i=1;i<nums.length;i=i+2) {
			int nextIndex = findIndex(i, nums);
			if(nextIndex==nums.length) {
				return;
			}
			int tmp = nums[i];
			nums[i] = nums[nextIndex];
			nums[nextIndex] = tmp;
		}
	}

	private int findIndex(int i, int[] nums) {
		int num = nums[i];
		while(i<nums.length) {
			if(nums[i]>num) break;
			i++;
		}
		return i;
	}
	
	private boolean valid(int[] nums) {
		if(nums.length<2) return true;
		for(int i=1;i<nums.length;i+=2) {
			boolean res = (nums[i] > nums[i-1]) &&
			(i+1<nums.length ? (nums[i]>nums[i+1]) : true);
			if(!res) return false;
		}
		return true;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6 };
		wiggleSort(nums);
		assertTrue(valid(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[] {1,2,4};
		wiggleSort(nums);
		assertTrue(valid(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[] { 6,5,4,3};
		wiggleSort(nums);
		assertTrue(valid(nums));
	}
	
	@Test
	public void test3() {
		int[] nums = new int[] { 6};
		wiggleSort(nums);
		assertTrue(valid(nums));
	}
	
	@Test
	public void test4() {
		int[] nums = new int[] {4,5,5,6};
		wiggleSort(nums);
		assertFalse(valid(nums));
	}
}
