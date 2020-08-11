package com.interview.vendor;

import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

// Given two integer arrays A and B, write a boolean function that returns true if we can find one
// element from A and one element from B, so that after we swap the two element, the sum of the two
// arrays are the same. Return false if we can't find
public class SwapAndSum {
    public boolean swap(int[] A, int[] B) {
        // Make sure A has smaller size so that less space is used
        if (A.length > B.length) return swap(B, A);
        Set<Long> set = new HashSet<>();
        long sumA = 0;
        for (int num1 : A) {
            set.add(Long.valueOf(num1));
            sumA += num1;
        }
        long sumB = 0;
        for (int num : B) {
            sumB += num;
        }
        // sum1 - sum2 = diff
        // num1 - num2 = - (diff / 2)
        // num1 = num2 - (diff / 2)
        long diff = sumA - sumB;
        if (diff % 2 != 0) return false;
        for (int num2 : B) {
            if (set.contains(num2 - diff / 2)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test0() {
        int A[] = {4, 1, 2, 1, 2}; // sum = 10
        int B[] = {6, 3, 3}; // sum = 12
        // swap 2 and 3
        // A { 4, 1, 3, 1, 2} sum = 11
        // B { 6, 2, 3} sum = 11
        assertTrue(swap(A, B));
    }
}
