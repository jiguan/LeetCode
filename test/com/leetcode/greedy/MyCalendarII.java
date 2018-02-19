package com.leetcode.greedy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyCalendarII {
    List<Integer[]> calender = new ArrayList<>();
    List<Integer[]> overlaps = new ArrayList<>();

    public boolean book(int start, int end) {
        for (Integer[] overlap : overlaps) {
            if (Math.max(overlap[0], start) < Math.min(overlap[1], end)) {
                return false;
            }
        }

        for(Integer[] book : calender) {
            int overlapStart = Math.max(start, book[0]);
            int overlapEnd = Math.min(end, book[1]);
            if(overlapStart < overlapEnd) {
                overlaps.add(new Integer[]{overlapStart, overlapEnd});
            }
        }

        calender.add(new Integer[]{start, end});
        return true;
    }

    @Test
    public void test0() {
        assertTrue(book(10, 20)); // returns true
        assertTrue(book(50, 60)); // returns true
        assertTrue(book(10, 40)); // returns true
        assertFalse(book(5, 15)); // returns false
        assertTrue(book(5, 10)); // returns true
        assertTrue(book(25, 55)); // returns true
    }

    @Test
    public void test1() {
        assertTrue(book(24, 40)); // returns true
        assertTrue(book(43, 50)); // returns true
        assertTrue(book(27, 43)); // returns true
        assertTrue(book(5, 21)); // returns true
        assertFalse(book(30, 40)); // returns false
        assertFalse(book(14, 29)); // returns false
        assertTrue(book(3, 19)); // returns true
        assertFalse(book(3, 14)); // returns false
        assertFalse(book(25, 39)); // returns false
        assertFalse(book(6, 19)); // returns false
    }
}
