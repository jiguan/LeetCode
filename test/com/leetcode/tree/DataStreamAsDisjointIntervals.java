package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import com.leetcode.util.Interval;
import com.leetcode.util.PrettyPrint;

public class DataStreamAsDisjointIntervals {
    TreeMap<Integer, Interval> map = new TreeMap<>();
    
    /** Initialize your data structure here. */
    public void SummaryRanges() {
        
    }

    public void addNum(int val) {
        if(map.containsKey(val)) return;
        Integer high = map.higherKey(val);
        Integer low = map.lowerKey(val);
        if(low!=null&&map.get(low).end+1==val && high!=null && high==val+1) {
            map.get(low).end = map.remove(high).end;
        } else if(low!=null && map.get(low).end+1==val) {
            map.get(low).end = val;
        } else if (high!=null && high==val+1) {
            Interval high_interval = map.remove(high);
            high_interval.start = val;
            map.put(val, high_interval);
        } else {
            map.put(val, new Interval(val, val));
        } 
    }

    public List<Interval> getIntervals() {
        return new LinkedList<>(map.values());
    }
    
    @Test
    public void test0() {
        addNum(1);
        addNum(3);
        addNum(7);
        PrettyPrint.print(getIntervals());
        addNum(2);
        PrettyPrint.print(getIntervals());
        addNum(6);
        PrettyPrint.print(getIntervals());
    }
}
