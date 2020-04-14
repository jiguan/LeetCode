package com.interview.uber;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

// Return the number of unique combination whose sum value equals to the target
// { 2, 3, 4, 7, 9, 9}, return 2, {2, 9} and {4, 7}
public class TwoSum {

    public int find(int[] nums, int target) {
        int res = 0;
        Set<Integer> map = new HashSet<>();
        for (int num : nums) {
            if (map.contains(target - num) && !map.contains(num)) {
                res++;
            }
            map.add(num);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {2, 3, 4, 7, 9, 9};
        int target = 11;
        int expected = find(nums, target);
        int actual = 2;
        assertEquals(Integer.valueOf(actual), Integer.valueOf(expected));
    }

}
