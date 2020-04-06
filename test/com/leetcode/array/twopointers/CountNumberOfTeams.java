package com.leetcode.array.twopointers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 * 
 * You have to form a team of 3 soldiers amongst them under the following rules:
 * 
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]). A team is
 * valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <=
 * i < j < k < n). Return the number of teams you can form given the conditions. (soldiers can be
 * part of multiple teams).
 */
public class CountNumberOfTeams {
    public int numTeams(int[] rating) {
        int res = 0;
        int n = rating.length;
        for (int mid = 1; mid < n - 1; ++mid) {
            // less[0] - nums of elements less than current value and left to the index
            // less[1] - nums on elements less than current value and right to the index
            int[] less = new int[2], greater = new int[2];
            for (int i = 0; i < n; ++i) {
                if (rating[i] > rating[mid]) {
                    if (i < mid) {
                        greater[0]++;
                    } else {
                        greater[1]++;
                    }
                } else if (rating[i] < rating[mid]) {
                    // rating[i] < rating[mid]
                    if (i < mid) {
                        less[0]++;
                    } else {
                        less[1]++;
                    }
                }
            }
            res += less[0] * greater[1] + greater[0] * less[1];
        }
        return res;
    }

    @Test
    public void test0() {
        int[] rating = {2, 5, 3, 4, 1};
        // (2,3,4), (5,4,1), (5,3,1)
        assertEquals(3, numTeams(rating));
    }
}
