package com.leetcode.array.interval;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.PriorityQueue;
import org.junit.Test;

/*
 * 1353. Maximum Number of Events That Can Be Attended
 * 
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at
 * startDayi and ends at endDayi.
 * 
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can
 * only attend one event at any time d.
 * 
 * Return the maximum number of events you can attend.
 * 
 * In another word: how many event you could attend, even partially
 * 
 */
public class MaximumNumberOfEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        // Store end day
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Sort by start day
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, currStartDay = 0, n = events.length;
        while (!pq.isEmpty() || i < n) {
            if (pq.isEmpty()) {
                currStartDay = events[i][0];
            }
            // Add open events
            while (i < n && events[i][0] <= currStartDay) {
                pq.offer(events[i++][1]);
            }
            pq.poll();
            ++res;
            // only one event can attend per day, move currStartDay to next day
            ++currStartDay;
            while (!pq.isEmpty() && pq.peek() < currStartDay) {
                pq.poll();
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[][] events = new int[][] {{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}};
        assertEquals(4, maxEvents(events));
    }
    
    @Test
    public void test1() {
        int[][] events = new int[][] {{1, 2}, {2, 3}, {3, 4}};
        assertEquals(3, maxEvents(events));
    }
}
