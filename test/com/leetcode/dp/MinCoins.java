package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinCoins {

    public int minCoins(int total, int[] coins) {
        int[] arr = new int[total+1];
        for(int i=1;i<arr.length;i++) {
            arr[i] = Integer.MAX_VALUE-1;
            for(int j=0;j<coins.length;j++) {
                int coin = coins[j];
                if(coin<=i) {
                    int a= 1 + arr[i-coin];
                    arr[i] = Math.min(a, arr[i]);
                }
            }
        }
        return arr[arr.length-1];
    }

    @Test
    public void test0() {
        int total = 30;
        int[] coins = new int[] {5, 10, 25};
        assertEquals(2, minCoins(total, coins));
    }

}


/*
int[] table = new int[total + 1];
for (int i = 1; i <= total; i++) {
    table[i] = Integer.MAX_VALUE;
}

for (int i = 1; i <= total; i++) {
    int min = Integer.MAX_VALUE;
    for (int j = 0; j < coins.length; j++) {
        int coin = coins[j];
        if (coin <= i) {
            min = Math.min(min, table[i - coin] + 1);
        }
    }
    table[i] = min;
}

return table[total];
*/