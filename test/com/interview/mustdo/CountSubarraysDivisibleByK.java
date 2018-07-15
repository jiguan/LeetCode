package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Given a sequence of n positive integers we need to count consecutive sub-sequences whose sum is divisible by k.
 * Constraints : N is up to 10^6 and each element up to 10^9 and K is up to 100
 * 
 * EXAMPLE : Let N=5 and K=3 and array be 1 2 3 4 1 Here answer is 4 Explanation : there exists, 4 sub-sequences whose
 * sum is divisible by 3, they are
 * 
 * [3], [1 2], [1,2 3], [2 3 4]
 *
 */
public class CountSubarraysDivisibleByK {
    public int count(int[] nums, int k) {
        int[] count = new int[k];
        // empty string % k = 0
        count[0] = 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            sum %= k;
            count[sum]++;
        }

        int res = 0;
        for (int i = 0; i < k; ++i) {
            // there are n in count[i], pick 2 of n, Cn2
            res += count[i] * (count[i] - 1) / 2;
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3, 4, 1};
        int k = 3;
        assertEquals(4, count(nums, k));
    }
}
