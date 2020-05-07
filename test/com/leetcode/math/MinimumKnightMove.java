package com.leetcode.math;

public class MinimumKnightMove {
    // https://leetcode.com/problems/minimum-knight-moves/discuss/392053/Here-is-how-I-get-the-formula-(with-graphs)
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x == 0 && y == 0) return 0;
        if (x + y == 1) return 3;
        int res = Math.max(Math.max((x + 1) / 2, (y + 1) / 2), (x + y + 2) / 3);
        res += (res ^ x ^ y) & 1;
        return res;
    }

}
