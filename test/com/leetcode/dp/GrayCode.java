package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class GrayCode {
    // n = 1: {0, 1}
    // n = 2: {00, 01, 10, 11}
    // n = 3: {000, 001, 010, 011, 111, 110, 101, 100}
    // => the first part is same, only need to | 1 from the back
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; ++i) {
            int size = res.size();
            for (int j = size - 1; j >= 0; --j) {
                res.add(res.get(j) | 1 << i);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int n = 0;
        List<Integer> expected = Arrays.asList(0);
        assertEquals(expected, grayCode(n));
    }

    @Test
    public void test1() {
        int n = 2;
        List<Integer> expected = Arrays.asList(0, 1, 3, 2);
        assertEquals(expected, grayCode(n));
    }
}
