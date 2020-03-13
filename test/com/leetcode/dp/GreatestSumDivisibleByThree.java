package com.leetcode.dp;

public class GreatestSumDivisibleByThree {
    /*
     * Add all together, if sum%3==0, return sum. if sum%3==1, remove the smallest number which has
     * n%3==1. if sum%3==2, remove the smallest number which has n%3==2.
     */
    public int maxSumDivThree(int[] nums) {
        int res = 0, leftOne = 20000, leftTwo = 20000;
        for (int n : nums) {
            res += n;
            if (n % 3 == 1) {
                leftTwo = Math.min(leftTwo, leftOne + n);
                leftOne = Math.min(leftOne, n);
            }
            if (n % 3 == 2) {
                leftOne = Math.min(leftOne, leftTwo + n);
                leftTwo = Math.min(leftTwo, n);
            }
        }
        if (res % 3 == 0) return res;
        if (res % 3 == 1) return res - leftOne;
        return res - leftTwo;
    }
    
    public int maxSumDivThree1(int[] nums) {
        // store the max sum
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int num : nums) {
            int[] tmp = new int[3];
            for (int i = 0; i < 3; ++i)
                tmp[(i + num) % 3] = Math.max(dp[(i + num) % 3], dp[i] + num);
            dp = tmp;
        }
        return dp[0];
    }
}
