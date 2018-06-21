package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountPrimes {
    public int countPrimes0(int n) {
        if (n < 3) return 0;
        boolean[] nums = new boolean[n];
        for (int i = 3; i < n; i = i + 2) {
            for (int j = 3; j <= i; j = j + 2) {
                long mul = i * j;
                if (mul >= n) break;
                nums[i * j] = true;
            }
        }
        int count = 1;
        for (int i = 3; i < n; i = i + 2) {
            if (nums[i] == false) {
                count++;
            }
        }
        return count;
    }

    public int countPrimes(int n) {
        if (n < 3) return 0;
        int count = 0;
        boolean[] arr = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (arr[i] == false) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    arr[i * j] = true;
                }
            }
        }
        return count;
    }

    @Test
    public void test0() {
        assertEquals(0, countPrimes(2));
    }
    @Test
    public void test1() {
        assertEquals(1, countPrimes(3));
    }
    @Test
    public void test2() {
        assertEquals(2, countPrimes(5));
    }
    @Test
    public void test3() {
        assertEquals(41537, countPrimes(499979));
    }
    @Test
    public void test4() {
        assertEquals(8, countPrimes(21));
    }

}
