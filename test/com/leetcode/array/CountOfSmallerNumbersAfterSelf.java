package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int[] counts = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // current index - original index
            indexes[i] = i;
        }
        partition(nums, indexes, 0, nums.length - 1, counts);
        for (int count : counts) {
            res.add(count);
        }
        return res;
    }

    // not moving nums in the original array, but storing the indexes after sorting
    private void partition(int[] nums, int[] indexes, int start, int end, int[] counts) {
        if (end <= start) {
            return;
        }
        int mid = (end - start) / 2 + start;
        partition(nums, indexes, start, mid, counts);
        partition(nums, indexes, mid + 1, end, counts);

        merge(nums, indexes, start, end, counts);
    }

    private void merge(int[] nums, int[] indexes, int start, int end, int[] counts) {
        int mid = (end - start) / 2 + start;
        int left = start;
        int right = mid + 1;
        // the number of elements in the right sorted part that are smaller than current element
        // from the left sorted part
        int rightcount = 0;
        // why don't we have leftcount?
        // e.g. 5,2,6,1
        // counts: (1,0) - (1,0)
        // after merge 2,5 with 1,6
        // left elements no impact on right side
        // but right element (1) increases counts of left elements

        int[] tmp = new int[end - start + 1];

        int index = 0;
        while (left <= mid || right <= end) {
            if (left > mid) {
                tmp[index++] = indexes[right++];
            } else if (right > end) {
                counts[indexes[left]] += rightcount;
                tmp[index++] = indexes[left++];

            } else if (nums[indexes[left]] <= nums[indexes[right]]) {
                // nums[indexes[left]] <= nums[indexes[right]]
                // if equals, also add rightcount which is for previous element
                counts[indexes[left]] += rightcount;
                tmp[index++] = indexes[left++];
            } else {
                tmp[index++] = indexes[right++];
                rightcount++;
            }
        }
        // update sorted index
        for (int i = 0; i < tmp.length; i++) {
            indexes[start + i] = tmp[i];
        }
    }

    @Test
    public void test0() {
        int[] nums = {5, 2, 6, 1};
        List<Integer> res = Arrays.asList(2, 1, 1, 0);
        assertEquals(res, countSmaller(nums));
    }

    @Test
    public void test2() {
        int[] nums = {100, 33, 67, 90, 100, 41};
        List<Integer> res = Arrays.asList(4, 0, 1, 1, 1, 0);
        assertEquals(res, countSmaller(nums));
    }

    @Test
    public void test1() {
        int[] nums = {26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69,
                81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41};
        List<Integer> res = Arrays.asList(10, 27, 10, 35, 12, 22, 28, 8, 19, 2, 12, 2, 9, 6, 12, 5,
                17, 9, 19, 12, 14, 6, 12, 5, 12, 3, 0, 10, 0, 7, 8, 4, 0, 0, 4, 3, 2, 0, 1, 0);
        assertEquals(res, countSmaller(nums));
    }
}
