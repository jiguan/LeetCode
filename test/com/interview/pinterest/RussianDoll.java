
package com.interview.pinterest;

import java.util.Arrays;
import java.util.Comparator;

// 20ms 76%
// O(n log n)
public class RussianDoll {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        int len = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] > b[0]) return 1;
                else if (a[0] < b[0]) return -1;
                else return b[1]-a[1];
            }
        });
        int[] dp = new int[len];
        int size = 0;
        for(int[] e: envelopes) {
            int index = Arrays.binarySearch(dp,0,size,e[1]);
            if(index < 0) index = -(index+1);
            dp[index] = e[1];
            if(index == size) size++;
        }
        return size;
    }
}