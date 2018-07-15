package com.interview.mustdo;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LeastFrequentlyUsed {

}

class LFUCache {
    // key - value
    Map<Integer, Integer> vals;
    // key - count
    Map<Integer, Integer> counts;
    // count - key
    Map<Integer, LinkedHashSet<Integer>> lists;
    int capacity;
    // init with invalid minTimes
    int minTimes = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>(capacity);
        counts = new HashMap<>(capacity);
        lists = new HashMap<>(capacity);
        lists.put(1, new LinkedHashSet<>());
    }

    // update the count
    public int get(int key) {
        if (!vals.containsKey(key)) {
            return -1;
        }
        int count = counts.get(key);
        // update key
        lists.get(count).remove(key);
        if (count == minTimes && lists.get(count).size() == 0) {
            minTimes++;
        }

        count++;
        counts.put(key, count);
        if (!lists.containsKey(count)) {
            lists.put(count, new LinkedHashSet<>());
        }
        lists.get(count).add(key);
        return vals.get(key);
    }

    // doesn't update count
    public void put(int key, int value) {
        if (capacity <= 0) return;

        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
        } else {
            if (vals.size() >= capacity) {
                int evictedKey = lists.get(minTimes).iterator().next();
                lists.get(minTimes).remove(evictedKey);
                vals.remove(evictedKey);
            }
            vals.put(key, value);
            counts.put(key, 1);
            minTimes = 1;
            lists.get(1).add(key);
        }
    }
}
