package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\."), arr2 = version2.split("\\.");
        int length = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < length; i++) {
            Integer num1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            Integer num2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;
            if (num1 != num2) return num1.compareTo(num2);
        }
        return 0;
    }

    @Test
    public void test0() {
        String version1 = "1.2", version2 = "13.37";
        assertEquals(-1, compareVersion(version1, version2));
    }

    @Test
    public void test1() {
        String version1 = "0000.1", version2 = "0000.1.02";
        assertEquals(-1, compareVersion(version1, version2));
    }

    @Test
    public void test2() {
        String version1 = "1.0", version2 = "1.1";
        assertEquals(-1, compareVersion(version1, version2));
    }
}
