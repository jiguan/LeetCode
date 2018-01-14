package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

/**
 * Design a data structure that supports all following operations in average
 * O(1) time.
 **/
public class InsertDeleteGetRandomO1 {
	// element - position
	private Map<Integer, Integer> position = new HashMap<>();
	private List<Integer> nums = new ArrayList<>();
	private Random random = new Random();

	/**
	 * Inserts a value to the set. Returns true if the set did not already
	 * contain the specified element.
	 */
	public boolean insert(int val) {
		if (position.containsKey(val))
			return false;
		position.put(val, nums.size());
		nums.add(val);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the
	 * specified element.
	 */
	public boolean remove(int val) {
		if (!position.containsKey(val))
			return false;

		int pos = position.get(val);
		if (pos != nums.size()) {
			// swap pos with last one
			int lastElement = nums.get(nums.size() - 1);
			nums.set(pos, lastElement);
			position.put(lastElement, pos);
		}
		nums.remove(nums.size() - 1);
		position.remove(val);

		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return nums.get(random.nextInt(nums.size()));
	}

	@Test
	public void test0() {
		assertTrue(insert(1));
		assertFalse(remove(2));
		assertTrue(insert(2));
		int random = getRandom();
		assertTrue(random > 0 && random < 2);
		assertTrue(remove(1));
		assertFalse(insert(2));
		assertTrue(getRandom() == 2);
	}
}
