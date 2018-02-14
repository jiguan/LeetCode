package com.leetcode.greedy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyCalendarI {
    List<Integer[]> calender = new ArrayList<>();

    public boolean book(int start, int end) {
        int low = 0, high = calender.size() - 1;

        while (low <= high) {
            int mid = (high - low) / 2 + low;
            Integer[] midElem = calender.get(mid);
            if (start < midElem[0]) {
                high = mid - 1;
            } else if (start > midElem[0]) {
                low = mid + 1;
            } else {
                return false;
            }
        }
        
        // if not insert to the head, check previous one
        if (low > 0 && start < calender.get(low - 1)[1]) {
            return false;
        }

        // if not insert to the end, check next one
        if (low < calender.size() && end > calender.get(low)[0]) {
            return false;
        }

        calender.add(low, new Integer[]{start, end});
        return true;
    }

    @Test
    public void test0() {
        assertTrue(book(10, 20));
        assertFalse(book(15, 25));
        assertTrue(book(20, 30));
    }

}
