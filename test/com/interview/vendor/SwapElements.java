package com.interview.vendor;

import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

// Given two integer arrays A and B, write a boolean function that returns true if we can find one
// element from A and one element from B, so that after we swap the two element, the sum of the two
// arrays are the same. Return false if we can't find
public class SwapElements {
    public boolean swap(int[] A, int[] B) {
        Set<Integer> set = new HashSet<>();
        int sumA = 0;
        for (int num : A) {
            set.add(num * 2);
            sumA += num;
        }
        int sumB = 0;
        for (int num : B) {
            sumB += num;
        }
        // sum1 - sum2 = diff
        // num1 - num2 = - (diff / 2)
        int diff = sumA - sumB;
        for (int num : B) {
            if (set.contains(2 * num - diff)) {
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
