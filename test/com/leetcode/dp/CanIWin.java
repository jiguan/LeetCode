package com.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) / 2 * maxChoosableInteger;
        if(sum < desiredTotal) return false;
        if(desiredTotal <=0) return true;
        
        return helper(desiredTotal, new boolean[maxChoosableInteger], new HashMap<>());
    }

    private boolean helper(int total, boolean[] used, Map<String, Boolean> map) {
        String curr = Arrays.toString(used);
        if (map.containsKey(curr)) return map.get(curr);
        for (int i = 0; i < used.length; ++i) {
            if (used[i] == false) {
                used[i] = true;

                if (total <= i + 1 || !helper(total - (i + 1), used, map)) {
                    map.put(curr, true);
                    used[i] = false;
                    return true;
                }
                used[i] = false;
            }
        }
        map.put(curr, false);
        return false;
    }
}
