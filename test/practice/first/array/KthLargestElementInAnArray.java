package practice.first.array;

public class KthLargestElementInAnArray {
	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		int low = 0;
		int high = nums.length - 1;
		while(low<high) {
			int pivot = partition(nums, low, high);
			if(pivot < k) {
				low = pivot + 1;
			} else if(pivot > k) {
				high = pivot - 1;
			} else {
				break;
			}
		}
		return nums[k];
	}
	
	private int partition(int[] nums, int low, int high) {
		int i = low, pivot = high;
		while(true) {
			while(i<high && nums[i]<nums[low]) {
				i++;
			}
			while(pivot>low && nums[low]<nums[pivot]) {
				pivot--;
			}
			if(i>=pivot) {
				break;
			}
			swap(nums, i, pivot);
		}
		swap(nums, low, pivot);
		return pivot;
	}
	
	private void swap(int[] nums, int i, int j) {
		nums[i] ^= nums[j];
		nums[j] ^= nums[i];
		nums[i] ^= nums[j];
	}
}
