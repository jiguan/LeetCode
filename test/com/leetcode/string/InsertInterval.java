package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.Interval;

public class InsertInterval {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();
		int i = 0;
		while (i < intervals.size()) {
			Interval tmp = intervals.get(i);
			if (tmp.end < newInterval.start) {
				result.add(tmp);
				i++;
			} else
				break;
		}
		while (i < intervals.size()) {
			Interval tmp = intervals.get(i);
			if (tmp.start < newInterval.end) {
				newInterval.start = Math.min(newInterval.start, tmp.start);
				newInterval.end = Math.max(newInterval.end, tmp.end);
				i++;
			} else {
				break;
			}
		}
		result.add(newInterval);
		while (i < intervals.size()) {
			result.add(intervals.get(i++));
		}
		return result;
	}

	@Test
	public void test0() {
		List<Interval> intervals = new LinkedList<>();
		intervals.add(new Interval(1, 3));
		intervals.add(new Interval(6, 9));

		Interval newInterval = new Interval(2, 5);
		List<Interval> result = insert(intervals, newInterval);
		List<Interval> expected = Arrays.asList(new Interval(1, 5), new Interval(6, 9));
		assertEquals(expected, result);
	}

	@Test
	public void test1() {
		List<Interval> intervals = new LinkedList<>();
		intervals.add(new Interval(1, 2));
		intervals.add(new Interval(3, 5));
		intervals.add(new Interval(6, 7));
		intervals.add(new Interval(8, 10));
		intervals.add(new Interval(12, 16));

		Interval newInterval = new Interval(4, 9);
		List<Interval> result = insert(intervals, newInterval);
		List<Interval> expected = Arrays.asList(new Interval(1, 2), new Interval(3, 10), new Interval(12, 16));
		assertEquals(expected, result);
	}

	@Test
	public void test2() {
		List<Interval> intervals = new LinkedList<>();
		Interval newInterval = new Interval(4, 9);
		List<Interval> result = insert(intervals, newInterval);
		List<Interval> expected = Arrays.asList(new Interval(4, 9));
		assertEquals(expected, result);
	}
}
