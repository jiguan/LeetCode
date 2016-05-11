package practice.first.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import practice.first.util.PrettyPrint;

public class PermutationsII {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums==null||nums.length==0) return result;
		Arrays.sort(nums);
		find(nums,new boolean[nums.length], new ArrayList<>(), result);
		return result;
	}
	
	private void find(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
		if(current.size()==nums.length) {
			result.add(new ArrayList<Integer>(current));
			return;
		}
		for(int i=0;i<nums.length;i++) {
			if(visited[i]==false) {
				if(i>0&&nums[i]==nums[i-1]&&!visited[i-1]) {
					continue;
				}
				current.add(nums[i]);
				visited[i] = true;
				find(nums, visited, current,result);
				current.remove(current.size()-1);
				visited[i] = false;
			}
		}
		
	}
    @Test
    public void test0() {
        int[] nums = new int[]{1,1,3};
        List<List<Integer>> result = permuteUnique(nums);
        for(List<Integer> list : result) {
            PrettyPrint.print(list);
        }
    }
}
