package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * 
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that
 * calls are being made to the system in chronological order (ie, the timestamp is monotonically
 * increasing). You may assume that the earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 */
public class DesignHitCounter {
    @Test
    public void test() {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        assertEquals(3, hitCounter.getHits(4));
        hitCounter.hit(300);
        assertEquals(4, hitCounter.getHits(300));
        assertEquals(3, hitCounter.getHits(301));
    }
}


class HitCounter {

    private int N = 300;
    private int[] timestamps = new int[N];
    private int[] hits = new int[N];

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        int index = (timestamp - 1) % N;
        if (timestamps[index] == timestamp) {
            hits[index]++;
        } else {
            timestamps[index] = timestamp;
            hits[index] = 1;
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int res = 0;
        for (int i = 0; i < N; ++i) {
            if (timestamp - timestamps[i] < N) {
                res += hits[i];
            }
        }
        return res;
    }
}
