package com.leetcode.array.backtrack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[s.length()];
        return dfs(s, set, 0, dp);
    }

    // https://leetcode.com/problems/word-break/discuss/169383/The-Time-Complexity-of-The-Brute-Force-Method-Should-Be-O(2n)-and-Prove-It-Below
    private boolean dfs(String s, Set<String> dict, int index, Boolean[] dp) {
        if (index == s.length()) {
            return true;
        }
        if (dp[index] != null) {
            return dp[index];
        }

        for (int i = index + 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(index, i)) && dfs(s, dict, i, dp)) {
                return dp[index] = true;
            }
        }
        return dp[index] = false;
    }
}
