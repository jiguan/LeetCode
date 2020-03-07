package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        int maxTimes = 0;
        int maxCounts = 0;
        for (char ch : tasks) {
            counts[ch - 'A']++;
            if (counts[ch - 'A'] > maxTimes) {
                maxTimes = counts[ch - 'A'];
                maxCounts = 1;
            } else if (counts[ch - 'A'] == maxTimes) {
                maxCounts++;
            }
        }

        // number of parts separated by letters with max times
        int partCount = maxTimes - 1;
        // multiple letters with max times
        int partLength = n - (maxCounts - 1);
        // A ? ? A ? ? A
        int emptySlots = partCount * partLength;
        // how many tasks we have to put into those slots
        int availableTasks = tasks.length - maxTimes * maxCounts;
        // check if not enough tasks available to fill all empty slots
        int idles = Math.max(0, emptySlots - availableTasks);
        return tasks.length + idles;
    }

    @Test
    public void test0() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        // A -> B -> idle -> A -> B -> idle -> A -> B.
        int expected = 8;
        assertEquals(expected, leastInterval(tasks, n));
    }
}
