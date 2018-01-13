package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new LinkedList<>();
        if (nums.length == 0) return res;

        for (int i = 0; i < nums.length; ++i) {
            int start = nums[i];
            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                ++i;
            }
            if (start == nums[i]) {
                res.add(start + "");
            } else {
                res.add(start + "->" + nums[i]);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{0, 1, 2, 4, 5, 7};
        List<String> expected = Arrays.asList("0->2", "4->5", "7");
        assertEquals(expected, summaryRanges(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{0, 1, 2, 4, 5, 7, 8};
        List<String> expected = Arrays.asList("0->2", "4->5", "7->8");
        assertEquals(expected, summaryRanges(nums));
    }
}
