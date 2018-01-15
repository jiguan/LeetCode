package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EliminationGame {
    public int lastRemaining(int n) {
        boolean left = true;
        int step = 1;
        int head = 1;
        while(n > 1) {
            // If starts from left or starts from right and the total remaining number % 2 == 1
            // head is changed
            if(left || n % 2 == 1) {
                head = head + step;
            }
            // Every round, reduce to half, steps for taking number change to double
            n /= 2;
            step *= 2;
            left = !left;
        }
        return head;
    }
    
    @Test
    public void test0() {
        int n = 9;
        assertEquals(6, lastRemaining(n));
    }
}
