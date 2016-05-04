package practice.first.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums==null||nums.length<3) return result;
		Arrays.sort(nums);
		for(int i=0;i<nums.length-3;i++) {
			for(int j=i+1;j<nums.length-2;j++) {
				int low = j+1, high = nums.length-1;
				while(low<high) {
					int sum = nums[i] + nums[j] + nums[low] + nums[high];
					if(sum==target) {
						result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
						while(low<high&&nums[low]==nums[low-1]) low++;
						while(low<high&&nums[high]==nums[high-1]) high--;
						low++;
						high--;
					} else if(sum<target) low++;
					else high--;
				}
			}
		} 
		return result;
	}
}
