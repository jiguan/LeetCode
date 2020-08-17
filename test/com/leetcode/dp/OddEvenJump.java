package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

/*
 * 975. Odd Even Jump
 * 
 * You are given an integer array A. From some starting index, you can make a series of jumps. The
 * (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th,
 * ...) jumps in the series are called even numbered jumps.
 * 
 * You may from index i jump forward to index j (with i < j) in the following way:
 * 
 * 1. During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <=
 * A[j] and A[j] is the smallest possible value. If there are multiple such indexes j, you can only
 * jump to the smallest such index j.
 * 
 * 2. During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >=
 * A[j] and A[j] is the largest possible value. If there are multiple such indexes j, you can only
 * jump to the smallest such index j.
 * 
 * 3. (It may be the case that for some index i, there are no legal jumps.)
 * 
 * A starting index is good if, starting from that index, you can reach the end of the array (index
 * A.length - 1) by jumping some number of times (possibly 0 or more than once.)
 * 
 * Return the number of good starting indexes
 * 
 */
public class OddEvenJump {


    /*
     * We need to jump higher and lower alternately to the end.
     * 
     * Take [5,1,3,4,2] as example.
     * 
     * If we start at 2, we can jump either higher first or lower first to the end, because we are
     * already at the end. higher(2) = true lower(2) = true
     * 
     * If we start at 4, we can't jump higher, higher(4) = false we can jump lower to 2, lower(4) =
     * higher(2) = true
     * 
     * If we start at 3, we can jump higher to 4, higher(3) = lower(4) = true we can jump lower to
     * 2, lower(3) = higher(2) = true
     * 
     * If we start at 1, we can jump higher to 2, higher(1) = lower(2) = true we can't jump lower,
     * lower(1) = false
     * 
     * If we start at 5, we can't jump higher, higher(5) = false we can jump lower to 4, lower(5) =
     * higher(4) = false
     * 
     * 
     * Complexity Time O(NlogN) Space O(N)
     */
    public int oddEvenJumps(int[] A) {
        int n = A.length, res = 1;
        boolean[] higher = new boolean[n], lower = new boolean[n];
        higher[n - 1] = true;
        lower[n - 1] = true;
        // value - index
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; --i) {
            // greater or equal / less or equal
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
            if (hi != null) {
                higher[i] = lower[(int) hi.getValue()];
            }
            if (lo != null) {
                lower[i] = higher[(int) lo.getValue()];
            }
            // we only need to care about higher, since odd jump can only go to higher
            // if it can reach the end at even jump, the first run, lower is set to true, second
            // run, higher set to true
            if (higher[i]) {
                res++;
            } else if (lower[i]) {
                System.out.println(i);
            }
            map.put(A[i], i);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] A = {10, 13, 12, 14, 15};
        assertEquals(2, oddEvenJumps(A));
    }

    @Test
    public void test1() {
        // i = 0 false
        // i = 1 true
        // i = 2 false
        // i = 3 true
        // i = 4, at the end already, true
        int[] A = {2, 3, 1, 1, 4};
        assertEquals(3, oddEvenJumps(A));
    }
}
