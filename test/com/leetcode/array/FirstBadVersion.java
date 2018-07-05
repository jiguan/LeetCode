package com.leetcode.array;

import org.junit.Test;

public class FirstBadVersion {
    private boolean[] versions;
    private boolean isBadVersion(int version) {
        return versions[version - 1];
    }

    public int firstBadVersion(int n) {
        int start = 1, end = n, res = -1;
        while (start < end) {
            int half = start + (end - start) / 2;
            if (isBadVersion(half)) {
                res = half;
                end = half - 1;
            } else {
                start = half + 1;
            }
        }
        return res;
    }

    public int firstBadVersion1(int n) {
        return find(1, n);
    }

    private int find(int start, int end) {
        if ((end - start) < 2) {
            if (isBadVersion(start)) return start;
            else
                return end;
        }
        int half = (start + end) / 2;
        if (isBadVersion(half)) {
            return find(start, half);
        } else {
            return find(half + 1, end);
        }
    }

    @Test
    public void test1() {
        prepareVersions(4, 2);
        System.out.println("Bad version: " + firstBadVersion(4));
    }

    @Test
    public void test2() {
        prepareVersions(4, 3);
        System.out.println("Bad version: " + firstBadVersion(4));
    }

    @Test
    public void test4() {
        prepareVersions(2126753390, 1702766719);
        System.out.println("Bad version: " + firstBadVersion(2126753390));
    }

    private void prepareVersions(int total, int bad) {
        versions = new boolean[total];
        for (int i = 0; i < total; i++) {
            if ((i + 1) >= bad) versions[i] = true;
            else
                versions[i] = false;
        }
    }

    private void print(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i + 1) + ":" + (arr[i] ? "X " : "O 	"));
        }
        System.out.println();
    }
}
