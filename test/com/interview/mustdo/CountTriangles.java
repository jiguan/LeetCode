package com.interview.mustdo;

import java.util.Arrays;

public class CountTriangles {
    public int findNumberOfTriangles(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; ++i) {
            int k = i + 2;
            for (int j = i + 1; j < n; ++j) {
                while (k < n && nums[i] + nums[j] > nums[k]) {
                    ++k;
                }
                res += k - 1 - j;
            }
        }
        return res;
    }
}
