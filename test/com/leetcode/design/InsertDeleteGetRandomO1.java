package com.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertDeleteGetRandomO1 {

}


class RandomizedSet {
    // value - index
    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> nums = new ArrayList<>();
    private java.util.Random rand = new java.util.Random();

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified
     * element.
     */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, nums.size());
            nums.add(val);
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int location = map.get(val);
            int n = nums.size();
            int lastVal = nums.get(n - 1);
            map.put(lastVal, location);
            nums.set(location, lastVal);

            map.remove(val);
            nums.remove(n - 1);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
