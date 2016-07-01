package practice.first.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len;i++) {
            //need to make sure target - 1 == target
            while(nums[i]>0 && nums[i]<=len && nums[nums[i] - 1] != nums[i]) {
                swap(nums[i] - 1, i, nums);
            }
        }
        
        for(int i=0;i<len;i++) {
            if(nums[i]!= i+1) {
                return i+1;
            }
        }
        return len + 1;
        
     }
     
     private void swap(int i, int j, int[] nums) {
         int tmp = nums[i];
         nums[i] = nums[j];
         nums[j] = tmp;
     }

    @Test
    public void test1() {
        int[] nums = new int[] {-1, 4, 2, 1, 9, 10};
        assertEquals(3, firstMissingPositive(nums));
    }
}
