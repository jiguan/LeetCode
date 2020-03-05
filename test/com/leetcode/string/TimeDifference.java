package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TimeDifference {
    private int minDiff(String str1, String str2) {
        int min1 = toMin(str1), min2 = toMin(str2);
        int diff = Math.abs(min1 - min2);
        if (diff > 12 * 60) {
            diff = 24 * 60 - diff;
        }
        return diff;
    }

    private int toMin(String str) {
        String[] strs = str.split(":");
        int res = Integer.parseInt(strs[1]);
        res += Integer.parseInt(strs[0]) * 60;
        return res;
    }

    @Test
    public void test0() {
        String str1 = "00:00", str2 = "23:59";
        assertEquals(1, minDiff(str1, str2));
    }

    @Test
    public void test2() {
        String str2 = "00:00", str1 = "23:59";
        assertEquals(1, minDiff(str1, str2));
    }

    @Test
    public void test1() {
        String str1 = "00:00", str2 = "01:59";
        assertEquals(119, minDiff(str1, str2));
    }

    @Test
    public void test3() {
        String str1 = "00:00", str2 = "12:00";
        assertEquals(12 * 60, minDiff(str1, str2));
    }
}
