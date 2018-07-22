package com.leetcode.util;

public class TernaryNode {
    TernaryNode left, middle, right;
    int val;
    TernaryNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
