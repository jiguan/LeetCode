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
    /**
     * Construct an array of Interval from its String representation
     * 
     * @param s
     *            - The String representation, e.g. "[ [1,4], [2,3], [3,4] ]"
     * @return An array of intervals
     */
    public static Interval[] build(String s) {
        s = s.replaceAll("\\s+", "");
        if (s.startsWith("[[")) {
            s = s.substring(2, s.length() - 2);
        }
        String[] subs = s.split("\\],\\[");
        Interval[] res = new Interval[subs.length];
        for (int i = 0; i < subs.length; ++i) {
            String sub = subs[i];
            String[] nums = sub.split(",");
            res[i] = new Interval(Integer.valueOf(nums[0]), Integer.valueOf(nums[1]));
        }
        return res;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + start;
        result = prime * result + end;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj instanceof Interval == false) {
            return false;
        }
        Interval another = (Interval) obj;
        return another.start == this.start && another.end == this.end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
