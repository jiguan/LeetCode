package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TaskScheduler {
    // Distance between same letter at least n
    public int leastInterval(char[] tasks, int n) {
        int[] letters = new int[26];
        for (char task : tasks) {
            letters[task - 'A']++;
        }

        Arrays.sort(letters);
        // same frequence with the highest, need to add on the tail rather than insert in the middle
        int addon = 0;
        for (int i = 25; i >= 0 && letters[i] == letters[25]; --i) {
            addon++;
        }

        return Math.max(tasks.length, (letters[25] - 1) * (n + 1) + addon);

    }

    @Test
    public void test0() {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        int n = 2;
        assertEquals(8, leastInterval(tasks, n));
    }

    @Test
    public void test1() {
        char[] tasks = { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int n = 2;
        assertEquals(16, leastInterval(tasks, n));
    }
}
