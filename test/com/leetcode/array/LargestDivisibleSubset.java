package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 1;i<nums.length;i++) {
            for(int j=i-1;j>=0;j--) {
                if(nums[i] % nums[j]==0) {
                    if(map.containsKey(nums[j])) {
                        Set<Integer> list = new HashSet<>(map.get(nums[j]));
                        list.add(nums[j]);
                        if(map.containsKey(nums[i])) {
                            list.addAll(map.get(nums[i]));
                        }
                        map.put(nums[i], list);
                    } else {
                        Set<Integer> list = new HashSet<>();
                        list.add(nums[j]);
                        list.add(nums[i]);
                        if(map.containsKey(nums[i])) {
                            list.addAll(map.get(nums[i]));
                        }
                        map.put(nums[i], list);
                    }
                }
            }
        }
        
        Set<Integer> res = new HashSet<>();
        for(Set<Integer> list : map.values()) {
            if(list.size()>res.size()) res = list;
        }
        return new ArrayList<Integer>(res);  
    }

    @Test
    public void test0() {
        int[] nums = new int[] {1, 2, 3};
        // [1,2] or [1,3]
        PrettyPrint.print(largestDivisibleSubset(nums));

    }
    
    @Test
    public void test1() {
        int[] nums = new int[] {1,2,3,4,6,24};
        // [1,2,4,24]
        PrettyPrint.print(largestDivisibleSubset(nums));

    }
}
