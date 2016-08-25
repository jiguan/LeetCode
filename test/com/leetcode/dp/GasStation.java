package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;


/**
 * 134. Gas Station
 * 
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * 
 * Note: The solution is guaranteed to be unique.
 *
 */

public class GasStation {
	// the amount of gas at station i is gas[i]
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0;
		int tank = 0;
		int residue = 0;
		for (int i = 0; i < gas.length; i++) {
			// cost[i] is the cost i to i+1
			tank += gas[i] - cost[i]; // tank left when arrive i+1
			residue += gas[i] - cost[i];
			if (tank < 0) { // cannot arrive i+1, reset start at i+1;
				start = i + 1;
				tank = 0;
			}
		}
		if (residue < 0)
			return -1;
		return start % gas.length;
	}

	@Test
	public void test() {
		int[] gas = new int[] { 1, 2, 3, 3 };
		int[] cost = new int[] { 2, 1, 5, 1 };
		assertEquals(3, canCompleteCircuit(gas, cost));
	}
}
