package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		int len = nums.length;
		int[] arr = new int[len];
		int max = 0;
		for(int i=0;i<len;i++) arr[i] = Integer.MAX_VALUE;
		for(int i=0;i<len;i++) {
			int element = nums[i];
			int low = 0, high = i;
			while(low<=high) {
				int middle = (high-low)/2 + low;
				if(arr[middle]>element) high = middle-1;
				else low = middle+1;
			}
			arr[low] = element; 
			max = Math.max(max, low+1);
		}
		return max;
	}
	
	@Test
	public void defaultTest() {
		int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
		assertEquals(4, lengthOfLIS(nums));
	}
}


/*	int n = nums.length;
if(n==0) return 0;
int[] arr = new int[n];
for(int i=0;i<n;i++) {
	arr[i] = 1;
}
for(int i=1;i<n;i++) {
	for(int j=0;j<i;j++) {
		if(nums[i]>nums[j]) {
			arr[i] = Math.max(arr[i], arr[j] + 1);
		}
	}
}
int result = 0;
for(int i=0;i<n;i++) {
	result = Math.max(result, arr[i]);
}
return result;
*/