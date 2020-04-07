package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TaskScheduler {
    // Distance between same letter at least n
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        int maxTimes = 0;
        int maxCount = 0;
        for (char ch : tasks) {
            counts[ch - 'A']++;
            if (counts[ch - 'A'] > maxTimes) {
                maxTimes = counts[ch - 'A'];
                maxCount = 1;
            } else if (counts[ch - 'A'] == maxTimes) {
                maxCount++;
            }
        }

        // a---a---a---a
        int partCount = maxTimes - 1;
        // ab12ab12ab12ab
        // a123a123a123a
        // 12 / 123 are the unfilledLength
        int unfilledLength = n - (maxCount - 1);
        int emptySlots = partCount * unfilledLength;
        // total number of other tasks except the one occurs maxTimes
        int otherTasks = tasks.length - maxTimes * maxCount;
        int idle = Math.max(0, emptySlots - otherTasks);

        return tasks.length + idle;
    }

    @Test
    public void test0() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        assertEquals(8, leastInterval(tasks, n));
    }

    @Test
    public void test1() {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        assertEquals(16, leastInterval(tasks, n));
    }
}
