package practice.first.array;

import org.junit.Test;

/*
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 Note:
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int index = m + n - 1;
		while(index>=0) {
			int a = m>0 ? nums1[m-1] : Integer.MIN_VALUE;
			int b = n>0 ? nums2[n-1] : Integer.MIN_VALUE;
			if(a > b) {
				nums1[index--] = a;
				m--;
			} else {
				nums1[index--] = b;
				n--;
			}
		}
	}
	
	
	@Test
	public void test1() {
		int[] nums1 = new int[20];
		System.arraycopy(new int[]{1, 8, 10, 12}, 0, nums1, 0, 4);
		int[] nums2 = new int[]{3, 5, 9, 11, 15};
		merge(nums1, 4, nums2, 5);
		print(nums1);
	}
	
	@Test
	public void test2() {
		int[] nums1 = new int[20];
		System.arraycopy(new int[]{}, 0, nums1, 0, 0);
		int[] nums2 = new int[]{};
		merge(nums1, 0, nums2, 0);
		print(nums1);
	}
	
	@Test
	public void test3 () {
		int[] nums1 = new int[20];
		System.arraycopy(new int[]{0}, 0, nums1, 0, 0);
		int[] nums2 = new int[]{1};
		merge(nums1, 0, nums2, 1);
		print(nums1);
	}
	 
	private void print(int[] nums) {
		for(int num : nums) {
			System.out.print(num+" ");
		}
		System.out.println();
	}
	
}
