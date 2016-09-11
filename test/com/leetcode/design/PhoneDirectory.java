package com.leetcode.design;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

/*
 * Design a Phone Directory which supports the following operations:
 *   get: Provide a number which is not assigned to anyone.
 *   check: Check if a number is available or not.
 *   release: Recycle or release a number. 
 *   If a number is released before, it should have higher priority to be released
 */
public class PhoneDirectory {
	Set<Integer> used = new HashSet<>();
	Queue<Integer> available = new LinkedList<>();
	int capacity = 0;

	public void init(int maxNumber) {
		capacity = maxNumber;
		for (int i = 0; i < maxNumber; i++) {
			available.offer(i);
		}
	}

	public int get() {
		if (available.isEmpty())
			return -1;
		int num = available.poll();
		used.add(num);
		return num;
	}

	public boolean check(int num) {
		if (num >= capacity || num < 0) {
			return false;
		}
		return !used.contains(num);
	}

	public void release(int num) {
		if (used.remove(num)) {
			available.offer(num);
		}
	}

	@Test
	public void test() {
		// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
		init(3);

		// It can return any available phone number. Here we assume it returns
		// 0.
		int first = get();
		assertFalse(check(first));
		// Assume it returns 1.
		int second = get();
		// It returns 2, the only number that is left.
		int third = get();

		// The number 2 is no longer available, so return false.
		assertFalse(check(third));

		// Release number 2 back to the pool.
		release(third);

		// Number 2 is available again, return true.
		assertTrue(check(third));

	}
}
