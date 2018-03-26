package com.leetcode.array;

import java.util.Arrays;

import org.junit.Test;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        int[][] pairs = new int[nums.length][2];

        for (int i = 0; i < nums.length; ++i) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, (a, b) -> (b[0] - a[0]));

        String[] res = new String[nums.length];
        int rank = 0;
        for (int i = 0; i < nums.length; ++i) {
            int index = pairs[i][1];
            switch (++rank) {
                case 1 :
                    res[index] = "Gold Medal";
                    break;
                case 2 :
                    res[index] = "Silver Medal";
                    break;
                case 3 :
                    res[index] = "Bronze Medal";
                    break;
                default :
                    res[index] = String.valueOf(rank);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{5, 4, 3, 2, 1};
        String[] expected = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"};
        com.leetcode.util.Arrays.assertEquals(expected, findRelativeRanks(nums));
    }
}
