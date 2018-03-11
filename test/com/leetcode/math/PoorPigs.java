package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        int maxBuckets = 1;
        while (maxBuckets < buckets) {
            pigs++;
            maxBuckets = maxBuckets * (minutesToTest / minutesToDie + 1);
        }
        return pigs;
    }

    @Test
    public void test0() {
        int buckets = 25;
        int minutesToDie = 15, minutesToTest = 60;
        int res = poorPigs(buckets, minutesToDie, minutesToTest);
        assertEquals(Integer.valueOf(2), Integer.valueOf(res));
    }
    
    @Test
    public void test1() {
        int buckets = 1;
        int minutesToDie = 1, minutesToTest = 1;
        int res = poorPigs(buckets, minutesToDie, minutesToTest);
        assertEquals(Integer.valueOf(0), Integer.valueOf(res));
    }
}
