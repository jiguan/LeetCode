package com.leetcode.array.interval;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import com.leetcode.util.Interval;

/*
 * Employee Free Time
 * 
 * We are given a list schedule of employees, which represents the working time for each employee.
 * 
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * 
 * Return the list of finite intervals representing common, positive-length free time for all
 * employees, also in sorted order.
 * 
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and
 * schedule[0][0][0] is not defined). Also, we wouldn't include intervals like [5, 5] in our answer,
 * as they have zero length.
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> all = new ArrayList<>();
        for (List<Interval> list : schedule) {
            all.addAll(list);
        }
        Collections.sort(all, (a, b) -> (a.start - b.start));
        List<Interval> res = new ArrayList<>();
        int prevEnd = all.get(0).end;
        for (Interval interval : all) {
            if (prevEnd < interval.start) {
                res.add(new Interval(prevEnd, interval.start));
            }
            prevEnd = Math.max(prevEnd, interval.end);
        }
        return res;
    }

    @Test
    public void test0() {
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 2), new Interval(5, 6)));
        schedule.add(Arrays.asList(new Interval(1, 3)));
        schedule.add(Arrays.asList(new Interval(4, 10)));

        List<Interval> expected = Arrays.asList(new Interval(3, 4));
        assertEquals(expected, employeeFreeTime(schedule));
    }
}
