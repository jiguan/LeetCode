package com.leetcode.bit;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/*
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 
 * Output: [2,7,14,8]
 * 
 * [0,1] = 1 xor 3 = 2; [1,2] = 3 xor 4 = 7; [0,3] = 1 xor 3 xor 4 xor 8 = 14; [3,3] = 8
 */
public class XorQueriesOfASubarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        // i - xor values from 0 to i
        int[] xor = new int[arr.length];
        xor[0] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            xor[i] = arr[i] ^ xor[i - 1];
        }
        int[] res = new int[queries.length];
        // [1 - 3] = [0 - 3] ^ [0, 0]
        for (int i = 0; i < queries.length; ++i) {
            int[] query = queries[i];
            res[i] = xor[query[1]];
            // start with non '0', minus operation needed
            if(query[0] != 0) {
                res[i] ^= xor[query[0] - 1];
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] arr = new int[] {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        int[] expecteds = {2, 7, 14, 8};
        int[] actual = xorQueries(arr, queries);
        assertArrayEquals(expecteds, actual);
    }
}
