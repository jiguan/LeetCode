package com.leetcode.array.rotatedsortedarray ;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMinimumInRotatedSortedArray {

    public int findMinAsec(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // Asec, min value, likely on the left
            int mid = start + (end - start) / 2;
         // @formatter:off
            /*
             * Two arrays:
             *   1    2    3    4    5
             * start      mid       end
             *   3    4    5    1    2
             *   
             *   no matter pivot is on left or right,
             *   start < mid
             */
         // @formatter:on
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return nums[start];
    }
    
    public int findMaxAsec(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // Asec, max value, likely on the right
            int mid = start + (end - start) / 2 + 1;
         // @formatter:off
            /*
             * Two arrays:
             *   1    2    3    4    5
             * start      mid       end
             *   3    4    5    1    2
             *   
             *   no matter pivot is on left or right,
             *   start < mid
             */
         // @formatter:on
            if (nums[start] > nums[mid]) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        return nums[end];
    }

    public int findMinDesc(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
         // @formatter:off
            /*
             * Two arrays:
             *   5    4    3    2    1
             * start      mid       end
             *   2    1    5    4    3
             *   
             *   no matter pivot is on left or right,
             *   mid > end
             */
         // @formatter:on
            
            // Decreasing, min value, mid + 1
            int mid = start + (end - start) / 2 + 1;
            if (nums[start] > nums[mid]) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return nums[end];
    }

    public int findMaxDesc(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // Desc, max value, likely on the left
            int mid = start + (end - start) / 2;
            // @formatter:off
            /*
             * Two arrays:
             *   5    4    3    2    1
             * start      mid       end
             *   2    1    5    4    3
             *   
             *   no matter pivot is on left or right,
             *   mid > end
             */
         // @formatter:on
            if (nums[mid] < nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[end];
    }

    @Test
    public void findMinAsecTest0() {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5};
        assertEquals(0, findMinAsec(nums));
    }

    @Test
    public void findMinAsecTest1() {
        int[] nums = new int[]{2, 3, 4, 5, 0, 1};
        assertEquals(0, findMinAsec(nums));
    }

    @Test
    public void findMinAsecTest2() {
        int[] nums = new int[]{4, 5, 0, 1, 2, 3};
        assertEquals(0, findMinAsec(nums));
    }
    
    @Test
    public void findMaxAsecTest0() {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5};
        assertEquals(5, findMaxAsec(nums));
    }

    @Test
    public void findMaxAsecTest1() {
        int[] nums = new int[]{2, 1, 0, 5, 4, 3};
        assertEquals(5, findMaxAsec(nums));
    }

    @Test
    public void findMaxAsecTest2() {
        int[] nums = new int[]{4, 5, 0, 1, 2, 3};
        assertEquals(5, findMaxAsec(nums));
    }
    
    @Test
    public void findMinDescTest0() {
        int[] nums = new int[]{5, 4, 3, 2, 1, 0};
        assertEquals(0, findMinDesc(nums));
    }

    @Test
    public void findMinDescTest1() {
        int[] nums = new int[]{3, 2, 1, 0, 5, 4};
        assertEquals(0, findMinDesc(nums));
    }

    @Test
    public void findMinDescTest2() {
        int[] nums = new int[]{2, 1, 0, 5, 4, 3};
        assertEquals(0, findMinDesc(nums));
    }
    
    @Test
    public void findMaxDescTest0() {
        int[] nums = new int[]{5, 4, 3, 2, 1, 0};
        assertEquals(5, findMaxDesc(nums));
    }

    @Test
    public void findMaxDescTest1() {
        int[] nums = new int[]{3, 2, 1, 0, 5, 4};
        assertEquals(5, findMaxDesc(nums));
    }

    @Test
    public void findMaxDescTest2() {
        int[] nums = new int[]{2, 1, 0, 5, 4, 3};
        assertEquals(5, findMaxDesc(nums));
    }
}
