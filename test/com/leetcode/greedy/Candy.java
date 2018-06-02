package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int len = ratings.length;

        int[] candy = new int[len];
        candy[0] = 1;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candy[i] = candy[i - 1] + 1;
            } else {
                candy[i] = 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i + 1] + 1, candy[i]);
            }
        }
        int total = 0;
        for (int num : candy) {
            total += num;
        }
        return total;
    }

    @Test
    public void test0() {
        int[] ratings = new int[]{1, 2, 2};
        assertEquals(4, candy(ratings));
    }

    @Test
    public void test1() {
        int[] ratings = new int[]{2, 2};
        assertEquals(2, candy(ratings));
    }

    @Test
    public void test2() {
        int[] ratings = new int[]{3, 2};
        assertEquals(3, candy(ratings));
    }
}
