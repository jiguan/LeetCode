package practice.first.array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
//Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

public class FindPeakElement {
	public int findPeakElement(int[] nums) {
		return find(0, nums.length-1, nums);
	}
	
	private int find(int start, int end, int[] nums) {
		if(end - start < 2) {
			return nums[start] > nums[end] ? start : end;
		}
		int mid = (end - start) / 2 + start;
		if (nums[mid-1] > nums[mid] && nums[mid] > nums[mid+1]) {
			return find(start, mid-1, nums);
		} else if (nums[mid-1] < nums[mid] && nums[mid] > nums[mid+1]) {
			return mid;
		} else {
			return find(mid+1, end, nums);
		}
	}

	@Test
	public void test0() {
		int[] nums = new int[]{1,3,2};
		assertEquals(1, findPeakElement(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{3,1,2};
		int result = findPeakElement(nums);
		assertTrue(0==result||2==result);
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{3,4,5,6};
		assertEquals(3, findPeakElement(nums));
	}
	@Test
	public void test3() {
		int[] nums = new int[]{7,6,5};
		assertEquals(0, findPeakElement(nums));
	}
	
	@Test
	public void test4() {
		int[] nums = new int[]{2,1};
		assertEquals(0, findPeakElement(nums));
	}
	
	@Test
	public void test5() {
		int[] nums = new int[]{1};
		assertEquals(0, findPeakElement(nums));
	}
}
