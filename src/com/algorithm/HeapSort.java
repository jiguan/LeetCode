package com.algorithm;

import org.junit.Test;

import practice.first.util.PrettyPrint;

public class HeapSort {
	/*
	 * iParent(i) = floor((i-1) / 2);
	 * 
	 * iLeftChild(i) = 2*i + 1;
	 * 
	 * iRightChild(i) = 2*i + 2;
	 */

	public void heapsort(int[] nums) {
		heapify(nums);

		int end = nums.length - 1;
		while (end >= 0) {
			// The nums[0] is the largest
			swap(0, end, nums);
			end--;
			siftDown(0, end, nums);
		}
	}

	// Build the heap in array a so that largest value is at the root
	private void heapify(int[] nums) {
		// get the last parent index
		int start = (nums.length - 1) / 2; 
		for(int i=start;i>=0;i--) {
			siftDown(i, nums.length - 1, nums);
		}
	}

	private void siftDown(int start, int end, int[] nums) {
		int root = start;
		while (2 * root + 1 <= end) {
			int child = 2*root+1; //left node
			int swap = root;
			//find out the index of the largest value 
			if (child <= end && nums[swap] > nums[child]) {  
				//if need min heap, change larger than to smaller than
				swap = child;
			}
			//child + 1 is right node
			if (child < end && nums[swap] > nums[child+1]) {
				swap = child+1;
			}
			if (swap != root) {
				swap(root, swap, nums);
				root = swap;
			} else {
				return;
			}
		}
	}

	private void swap(int i, int j, int[] nums) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{5,3,2,6,1,7};
		heapsort(nums);
		PrettyPrint.print(nums);
	}

}
