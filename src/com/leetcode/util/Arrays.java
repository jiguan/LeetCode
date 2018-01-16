package com.leetcode.util;

import java.util.HashSet;
import java.util.Set;

public class Arrays {
    public static boolean equals(int[] expected, int[] actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.length != actual.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) return false;
        }
        return true;
    }

    public static boolean containSameElements(int[] expected, int[] actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.length != actual.length) return false;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < expected.length; i++) {
            set.add(expected[i]);
        }
        for (int i = 0; i < actual.length; ++i) {
            if (!set.remove(actual[i])) {
                return false;
            }
        }
        return true;
    }
}
