package practice.first.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++) {
            int low = i+1, high = nums.length-1;
            if(i>0&&nums[i]==nums[i-1]) continue;
            while(low<high) {
                int sum = nums[i] + nums[low] + nums[high];
                if(sum==0) {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while(low<nums.length-1&&nums[low]==nums[low+1]) low++;
                    while(high>0&&nums[high]==nums[high-1]) high--;
                    low++;
                    high--;
                }
                else if(sum<0) low++;
                else high--;
            }
        }
        return result;
    }
    
    private void prettyPrint(List<List<Integer>> lists) {
        for(List<Integer> list : lists) {
            System.out.print("[");
            for(int num : list) {
                System.out.print(num+",");
            }
            System.out.println("]");
        }
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        prettyPrint(threeSum(nums));
    }
}
