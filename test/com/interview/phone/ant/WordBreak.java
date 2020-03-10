package com.interview.phone.ant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int end = 1; end <= s.length(); ++end) {
            for (int start = 0; start < end; start++) {
                dp[end] = dp[start] && dict.contains(s.substring(start, end));
                if(dp[end]) break;
            }
        }
        return dp[s.length()];  
    }
}
