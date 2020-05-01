package com.leetcode.util;

public class Master {
    private String target;

    public Master(String target) {
        this.target = target;
    }

    public int guess(String word) {
        int matches = 0;
        for (int i = 0; i < word.length(); ++i) {
            if (target.charAt(i) == word.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }
}
