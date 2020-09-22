package com.interview.vendor;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.junit.Test;

public class RateLimiter {

    @Test
    public void test0() {
        String[] errors = {"1:200:3", "2:200:7", "2:300:6"};
        Monitor monitor = new Monitor(5, 10);
        monitor.check(errors);
        // at time 2, 2 alerts should be triggered
    }
    
}

class Monitor {
    int k;
    int timeWindow;

    Map<String, Queue<Map.Entry<Integer, Integer>>> map = new HashMap<>();
    Map<String, Integer> counts = new HashMap<>();

    public Monitor(int k, int timeWindow) {
        this.k = k;
        this.timeWindow = timeWindow;
    }

    // Run every min and get all new error logs within this one min
    public void check(String[] errors) {
        if (errors == null) return;
        for (String error : errors) {
            String[] arr = error.split(":");
            int timestamp = Integer.valueOf(arr[0]);
            String errorCode = arr[1];
            int occur = Integer.valueOf(arr[2]);
            if (!map.containsKey(errorCode)) {
                map.put(errorCode, new LinkedList<>());
                counts.put(errorCode, 0);
            }
            // timestamp - occurrence
            Queue<Map.Entry<Integer, Integer>> queue = map.get(errorCode);
            while (!queue.isEmpty()) {
                // drop all counts outside the time window
                if (timestamp - queue.peek().getKey() > timeWindow) {
                    counts.put(errorCode, counts.get(errorCode) - queue.poll().getValue());
                } else {
                    break;
                }
            }
            counts.put(errorCode, counts.get(errorCode) + occur);
            queue.offer(new AbstractMap.SimpleEntry<>(timestamp, occur));
            if (counts.get(errorCode) > k) {
                System.out.println(String.format("Time: %d alert for errorcode %s occured %d times", timestamp, errorCode, counts.get(errorCode)));
            }
        }
    }
}