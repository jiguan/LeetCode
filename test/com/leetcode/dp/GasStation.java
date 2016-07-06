package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int tank = 0;
        int residue = 0;
        for (int i = 0; i < gas.length; i++) {
            // cost[i] is the cost to i+1
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
    
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        set2.retainAll(set1);
        int[] res = new int[set2.size()];
        int i=0;
        for(int num : set2) {
            res[i++] = num;
        }
       return res;
    }
    

    @Test
    public void test1() {
        int[] nums1 = new int[1];
        nums1[0] = 1;
        int[] nums2 = new int[0];
        intersection(nums1, nums2);
    }

    @Test
    public void test0() {
        int[] gas = new int[] {1, 2, 3, 3};
        int[] cost = new int[] {2, 1, 5, 1};
        assertEquals(3, canCompleteCircuit(gas, cost));
    }
}
