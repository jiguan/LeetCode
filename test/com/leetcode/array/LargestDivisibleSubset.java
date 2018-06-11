package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];

        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] > dp[maxIndex]) {
                        maxIndex = i;
                    }
                }
            }
        }

        int lastNum = nums[maxIndex];
        int occurs = dp[maxIndex];
        for (int i = maxIndex; i >= 0 && occurs >= 0; i--) {
            if (lastNum % nums[i] == 0 && dp[i] == occurs) {
                res.add(nums[i]);
                lastNum = nums[i];
                occurs--;
            }
        }
        return res;
    }

    public List<Integer> largestDivisibleSubset0(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (map.containsKey(nums[j])) {
                        Set<Integer> list = new HashSet<>(map.get(nums[j]));
                        list.add(nums[j]);
                        if (map.containsKey(nums[i])) {
                            list.addAll(map.get(nums[i]));
                        }
                        map.put(nums[i], list);
                    } else {
                        Set<Integer> list = new HashSet<>();
                        list.add(nums[j]);
                        list.add(nums[i]);
                        if (map.containsKey(nums[i])) {
                            list.addAll(map.get(nums[i]));
                        }
                        map.put(nums[i], list);
                    }
                }
            }
        }

        Set<Integer> res = new HashSet<>();
        for (Set<Integer> list : map.values()) {
            if (list.size() > res.size()) res = list;
        }
        return new ArrayList<Integer>(res);
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3};
        // [1,2] or [1,3]
        PrettyPrint.print(largestDivisibleSubset(nums));

    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3, 4, 6, 24};
        // [1,2,4,24]
        PrettyPrint.print(largestDivisibleSubset(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{4, 8, 10, 240};
        // [4, 8, 240]
        PrettyPrint.print(largestDivisibleSubset(nums));
    }
    
    @Test
    public void test3() {
        int[] nums = new int[]{2,3,8,9,27};
        // [3, 9, 27]
        PrettyPrint.print(largestDivisibleSubset(nums));
    }
}
