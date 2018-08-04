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
        // since we are trying to get the sum % k == 0,
        // if we already have such a sum, we can choose not to select another one,
        // so we always have "" as one option
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
            // select 2 sums which after mod are same, the sum between them mode k = 0
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

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3, 1};
        // [1,2], [1,2,3], [3], [2,3,1]
        int k = 3;
        assertEquals(4, count(nums, k));
    }
    
    @Test
    public void test2() {
        int[] nums = new int[]{1, 1};
        int k = 2;
        assertEquals(1, count(nums, k));
    }
}
