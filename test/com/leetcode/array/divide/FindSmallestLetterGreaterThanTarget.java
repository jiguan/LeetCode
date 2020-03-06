package com.leetcode.array.divide;

public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0, end = letters.length;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (letters[mid] - target > 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return letters[start % letters.length];
    }
}
