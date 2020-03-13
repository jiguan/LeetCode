package com.leetcode.binearysearch;

public class AndroidUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        // Skip array represents number to skip between two pairs
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean visit[] = new boolean[10];
        int res = 0;
        // DFS search each length from m to n
        for(int remain = m; remain <= n; ++remain) {
            res += dfs(visit, skip, 1, remain - 1) * 4;    // 1, 3, 7, 9 are symmetric
            res += dfs(visit, skip, 2, remain - 1) * 4;    // 2, 4, 6, 8 are symmetric
            res += dfs(visit, skip, 5, remain - 1);        // 5
        }
        return res;
    }
    
    private int dfs(boolean visit[], int[][] skip, int index, int remain) {
        if(remain < 0) return 0;
        if(remain == 0) return 1;
        visit[index] = true;
        int rst = 0;
        for(int i = 1; i <= 9; ++i) {
            // If vis[i] is not visited and (two numbers are adjacent or skip number is already visited)
            if(!visit[i] && (skip[index][i] == 0 || (visit[skip[index][i]]))) {
                rst += dfs(visit, skip, i, remain - 1);
            }
        }
        visit[index] = false;
        return rst;
    }
}
