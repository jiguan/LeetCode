package com.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/*
 * Strobogrammatic Number A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Write a function to determine if a number is strobogrammatic. The number is represented as a
 * string.
 * 
 * Example 1:
 * 
 * Input: "69" Output: true Example 2:
 * 
 * Input: "88" Output: true Example 3:
 * 
 * Input: "962" Output: false
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Set<String> set = new HashSet<>();
        set.add("0");
        set.add("1");
        set.add("8");
        set.add("00");
        set.add("11");
        set.add("88");
        set.add("69");
        set.add("96");

        for (int left = 0, right = num.length() - 1; left <= right; left++, right--) {
            if (!set.contains(num.charAt(left) + "" + num.charAt(right)))
                return false;
        }

        return true;
    }
}
