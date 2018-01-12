package com.leetcode.permutation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int permutationNum = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            // Total permutation: A44 = 24
            permutationNum *= i;
        }
        StringBuffer buffer = new StringBuffer();
        // Go over each digit
        for (int i = 0; i < n; i++) {
            // how many permutation behind current i, A33 = 6
            permutationNum /= (n - i);
            // each value of i can cover permutationNum
            // When k is 7, need to make sure get the second one
            int index = (k - 1) / permutationNum; // decide the low boundary
            buffer.append(nums.remove(index));
            k -= index * permutationNum;
        }
        return buffer.toString();
    }

    @Test
    public void test0() {
        int n = 4, k = 3;
        assertEquals("1324", getPermutation(n, k));
    }

    @Test
    public void test1() {
        int n = 2, k = 1;
        assertEquals("12", getPermutation(n, k));
    }

}
