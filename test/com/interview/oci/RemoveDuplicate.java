package com.interview.oci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class RemoveDuplicate {
    private List<Integer> removeDup(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null)
            return res;
        int i = 0;
        while (i < nums.length) {
            int j = i;
            // find the next different element's index
            while (j < nums.length && nums[j] == nums[i]) {
                ++j;
            }
            if (j == i + 1) {
                res.add(nums[i]);
            }
            i = j;
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {1, 1, 1, 1, 1};
        assertTrue(removeDup(nums).isEmpty());
    }

    @Test
    public void test1() {
        int[] nums = {1, 1, 1, 1, 1, 2};
        assertEquals(Arrays.asList(2), removeDup(nums));
    }

}
