package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CountPrimes {
    // O(n * log(log(n)))
    public int countPrimes(int n) {
        if (n < 3) return 0;
        // for n = 2
        int count = 1;
        boolean[] arr = new boolean[n];
        // i = 3, i*j = 6, 9, 12, .., n/3
        // i = 5, i*j = 10, 15, 20, .., n/5
        // ..
        // n/3 + n/5 + n/7, (n/9 is skipped), n/11 => O(n * log(log(n)))
        for (int i = 3; i < n; i += 2) {
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
