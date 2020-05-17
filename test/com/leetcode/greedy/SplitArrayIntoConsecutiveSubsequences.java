package com.leetcode.greedy;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class SplitArrayIntoConsecutiveSubsequences {
    // Given an array nums sorted in ascending order
    public boolean isPossible0(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            } else if (appendfreq.getOrDefault(num, 0) > 0) {
                appendfreq.put(num, appendfreq.get(num) - 1);
                appendfreq.put(num + 1, appendfreq.getOrDefault(num + 1, 0) + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                appendfreq.put(num + 3, appendfreq.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
            freq.put(num, freq.get(num) - 1);
        }
        return true;
    }
    
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        for(int num : nums) {
            if(!freq.containsKey(num)) {
                continue;
            }
            int minOccur = 1;
            int tmp = num;
            while(freq.containsKey(tmp) && freq.get(tmp) >= minOccur) {
                minOccur = Math.max(minOccur, freq.get(tmp));
                freq.put(tmp, freq.get(tmp) - 1);
                if(freq.get(tmp) == 0) {
                    freq.remove(tmp);
                }
                tmp++;
            }
            if(tmp - num < 3) return false;
        }
        return freq.isEmpty();
    }

    @Test
    public void test0() {
        int[] nums = {1, 2, 3, 3, 4, 4, 5, 5};
        assertTrue(isPossible(nums));
    }

    @Test
    public void test1() {
        int[] nums = {1, 2, 3, 3, 4, 5};
        assertTrue(isPossible(nums));
    }
}
