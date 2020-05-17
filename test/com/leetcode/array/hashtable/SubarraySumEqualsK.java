package com.leetcode.array.hashtable;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SubarraySumEqualsK {
	public int subarraySum(int[] nums, int k) {
		int res = 0, sum = 0;
		/*
		 * 1. Hashmap<sum[0,i - 1], frequency>
		 * 
		 * 2. sum[i, j] = sum[0, j] - sum[0, i - 1] --> sum[0, i - 1] = sum[0, j] -
		 * sum[i, j] --> sum[0, i - 1] = sum - k
		 * 
		 * 3. now, we have k and sum. As long as we can find a sum[0, i - 1], we then
		 * get a valid subarray which is as long as we have the hashmap-key, we then get
		 * a valid subarray
		 * 
		 * 4. Why don't map.put(sum[0, i - 1], 1) every time ? if all numbers are
		 * positive, this is fine if there exists negative number, there could be preSum
		 * frequency > 1
		 */

		Map<Integer, Integer> map = new HashMap<>();
		// Must use this way, since [0, 0, 0], target = 0 is not just adding 1
		map.put(0, 1);
		for (int num : nums) {
			sum += num;
			if (map.containsKey(sum - k)) {
				// subarry's sum == sum -k exists, so that sum - (sum - k) == k
				res += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return res;
	}

	@Test
	public void test0() {
		int[] nums = { 1, 1, 1 };
		int expected = 2;
		assertEquals(expected, subarraySum(nums, 2));
	}

	@Test
	public void test1() {
		int[] nums = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int expected = 55;
		assertEquals(expected, subarraySum(nums, 0));
	}
}
