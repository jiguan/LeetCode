package com.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

public class InsertDeleteGetRandomO1DuplicatesAllowed {
    @Test
    public void test0() {
        RandomizedCollection collection = new RandomizedCollection();
        collection.insert(4);
        collection.insert(3);
        collection.insert(4);
        collection.insert(2);
        collection.insert(4);
        collection.remove(4);
        collection.remove(3);
        collection.remove(4);
        collection.remove(4);
    }
}


class RandomizedCollection {

    // value - index
    private Map<Integer, Set<Integer>> map = new HashMap<>();
    private List<Integer> nums = new ArrayList<>();
    private java.util.Random random = new Random();

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the
     * specified element.
     */
    public boolean insert(int val) {
        boolean res = false;
        if (!map.containsKey(val)) {
            map.put(val, new HashSet<>());
            res = true;
        }
        map.get(val).add(nums.size());
        nums.add(val);
        return res;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val).iterator().next();
            map.get(val).remove(index);
            if(index < nums.size() - 1) {
                int lastVal = nums.get(nums.size() - 1);
                nums.set(index, lastVal);
                map.get(lastVal).remove(nums.size() - 1);
                map.get(lastVal).add(index);                
            }
            nums.remove(nums.size() - 1);
            if (map.get(val).isEmpty()) map.remove(val);

            return true;
        } else {
            return false;
        }

    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}
