package practice.first.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        while(low<high) {
            int mid = (low+high) /2;
            if(nums[mid]>nums[2])
        }
        
        
        
        while(low<=high) {
        }
        return -1;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{5,6,7,8,9,1,2,3,4};
        assertEquals(1, search(nums, 6));
        assertEquals(7, search(nums, 3));
        assertEquals(-1, search(nums, 0));
    }
    
    @Test
    public void test1() {
        int[] nums = new int[]{3,4,5,6,7,8,9,1,2};
        assertEquals(7, search(nums, 1));
        assertEquals(2, search(nums, 5));
    }
    
    @Test
    public void test2() {
        int[] nums = new int[]{7,8,9,1,2,3,4,5,6};
        assertEquals(2, search(nums, 9));
        assertEquals(5, search(nums, 3));
    }

}
