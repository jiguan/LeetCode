package com.leetcode.greedy;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class SplitArrayIntoConsecutiveSubsequences {
    // The idea is we have the preference in the following order
    // - Extend length2 chain to length3
    // - Extend length1 chain to length2
    // - Extend old >= length3 chain to new >= length3 chain
    // - Create a new chain.
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return false;
        }
        int curNum = 0, preNum = Integer.MIN_VALUE;
        int curEndL1 = 0, curEndL2 = 0, curEndL3 = 0;
        int preEndL1 = 0, preEndL2 = 0, preEndL3 = 0;
        int m = nums.length;
        for (int i = 0; i < m;) {
            curNum = nums[i];
            // Number of repeated elements ending at curNum.
            int count = 0;
            while (i < m && curNum == nums[i]) {
                i++;
                count++;
            }
            if (preNum + 1 != curNum) {
                if (preEndL1 != 0 || preEndL2 != 0) {
                    // If it's not continuous (preNum + 1 != curNum) and
                    // chains ending at pre number with length 1 and 2
                    // are non-zero, just return false.
                    return false;
                }
                curEndL1 = count;
                curEndL2 = curEndL3 = 0;
            } else {
                if (count < preEndL1 + preEndL2) {
                    // When it's continuous (preNum + 1 == curNum)
                    // if current number cannot cover the number of chains ending at
                    // pre number with length 1 and 2, just return false.
                    return false;
                }
                // Firstly extended length 1 chain to be length 2.
                curEndL2 = preEndL1;
                // Then extended length 2 chain to be length 3.
                curEndL3 = preEndL2;

                // Then residual curNum will be distributed to either preEndL3 chains or create new
                // chains.
                // Should prefer extend old chains first.
                int residual = count - preEndL1 - preEndL2;
                int numToExtendOldLongChain = Math.min(preEndL3, residual);
                curEndL3 += numToExtendOldLongChain;

                // Then create new chain.
                curEndL1 = Math.max(0, residual - numToExtendOldLongChain);
            }
            preNum = curNum;
            preEndL1 = curEndL1;
            preEndL2 = curEndL2;
            preEndL3 = curEndL3;
        }
        return preEndL1 == 0 && preEndL2 == 0;
    }

    public boolean isPossible0(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (!freq.containsKey(num)) {
                continue;
            }
            int minOccur = 1;
            int tmp = num;
            while (freq.containsKey(tmp) && freq.get(tmp) >= minOccur) {
                minOccur = Math.max(minOccur, freq.get(tmp));
                freq.put(tmp, freq.get(tmp) - 1);
                if (freq.get(tmp) == 0) {
                    freq.remove(tmp);
                }
                tmp++;
            }
            if (tmp - num < 3)
                return false;
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
