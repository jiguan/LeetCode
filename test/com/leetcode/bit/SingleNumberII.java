package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int sum = 0;
        for(int i=0;i<32;i++) {
            int tmp = 0;
            for(int num : nums) {
                tmp += (num >> i) & 1;
            }
            sum |= tmp%3 << i;
        }

        return sum;
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{3,2,2,2};
        assertEquals(3, singleNumber(nums));
    }
}
