package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class NumberComplement {
    public int findComplement1(int num) {
    	// if num is 101, , the hightestOneBit is 100, shift one and minus 1 to create the int 111
        int allOne = (Integer.highestOneBit(num) << 1) - 1;
        return ~num & allOne;
    }
    
    public int findComplement(int num) {
    	// the num is 101, the result is 010, their sum is 111
    	int sum = 0;
    	while(sum < num) {
    		sum = (sum << 1) | 1;
    	}
    	return sum - num;
    }
    
    public int findShortestSubArray(int[] nums) {
        int degree = 0;
        Map<Integer, Integer> times = new HashMap<>();
        Map<Integer, Integer[]> positions = new HashMap<>();
        for(int i=0;i<nums.length;++i) {
            int time = 1;
            Integer[] pos = null;
            if(times.containsKey(nums[i])) {
                time = times.get(nums[i]);
                ++time;
                pos = positions.get(nums[i]);
                pos[1] = i;
            } else {
                pos = new Integer[2];
                pos[0] = i;
                pos[1] = i;
            }
            degree = time > degree ? time : degree;
            times.put(nums[i], time);
            positions.put(nums[i], pos);
        }
        
        int result = nums.length;
        for(Entry<Integer, Integer> entry : times.entrySet()) {
            if(entry.getValue() == degree) {
                Integer[] pos = positions.get(entry.getKey());
                int distance = pos[1] - pos[0] + 1;
                result = distance < result ? distance : result;
            }
        }
        return result;
    }
    
    @Test
    public void test0() {
    	int num = 5, expect = 2;
    	assertEquals(expect, findComplement(num));
    }
    
    @Test
    public void test1() {
    	int[] nums = new int[]{1,2,2,3,1,4,2};
    	assertEquals(6, findShortestSubArray(nums));
    }
}
