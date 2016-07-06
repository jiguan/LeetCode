package com.leetcode.util;

public class Interval {
    public int start, end;

    public Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int s, int e) {
        start = s;
        end = e;
    }
    
    @Override
    public String toString() {
        return "["+start+", "+end+"]";
    }
}
