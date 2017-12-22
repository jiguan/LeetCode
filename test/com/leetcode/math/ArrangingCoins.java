package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrangingCoins {
    public int arrangeCoins0(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = (start + end) >> 1;
            int sum = (mid + 1) * mid / 2;
            if (sum < n) {
                start = mid + 1;
            } else if (sum > n){
                end = mid -1;
            } else {
                start = mid;
                break;
            }
        }
        return start;
    }
    
    /*
     *  n = (x + 1) * x / 2;
     *  x = (-1 + Math.sqrt(1 + 8n)) / 2
     */
    public int arrangeCoins(int n) {
        return (int)(Math.sqrt(8 * (long) n + 1) - 1) / 2;
    }

    @Test
    public void test0() {
        int n = 5, expect = 2;
        assertEquals(expect, arrangeCoins(n));
    }
    
    @Test
    public void test1() {
        int n = 6, expect = 3;
        assertEquals(expect, arrangeCoins(n));
    }
    
    @Test
    public void test2() {  
        int n = 2, expect = 1;
        assertEquals(expect, arrangeCoins(n));
    }
}
