package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>(n);
        for (int i = 1, curr = 1; i <= n; ++i) {
            ans.add(curr);
            // iterator all numbers starting with curr
            if (curr * 10 <= n) {
                curr *= 10;
            } else {
                // find next number
                while (curr % 10 == 9 || curr == n)
                    curr /= 10;
                curr++;
            }
        }
        return ans;
    }

    @Test
    public void test0() {
        int n = 13;
        List<Integer> expected = Arrays.asList(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(expected, lexicalOrder(n));
    }
}
