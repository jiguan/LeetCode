package com.leetcode.design;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {

}


class TimeMap {

    private Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null) {
            return "";
        }
        Integer floor = treeMap.floorKey(timestamp);
        if (floor == null) {
            return "";
        }
        return treeMap.get(floor);
    }
}
