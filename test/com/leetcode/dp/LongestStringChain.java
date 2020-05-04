package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

// word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to
// make it equal to word2.
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) return 0;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        // predecessor - chain length
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            int count = 0;
            for (int i = 0; i < word.length(); ++i) {
                String sub = word.substring(0, i) + word.substring(i + 1);
                count = Math.max(count, map.getOrDefault(sub, 0) + 1);
            }
            map.put(word, count);
            res = Math.max(res, count);
        }
        return res;
    }



    @Test
    public void test0() {
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        int expected = 4;
        assertEquals(expected, longestStrChain(words));
    }

    @Test
    public void test1() {
        String[] words = {"wnyxmflkf", "xefx", "usqhb", "ttmdvv", "hagmmn", "tmvv", "pttmdvv",
                "nmzlhlpr", "ymfk", "uhpaglmmnn", "zckgh", "hgmmn", "isqxrk", "isqrk", "nmzlhpr",
                "uysyqhxb", "haglmmn", "xfx", "mm", "wymfkf", "tmdvv", "uhaglmmn", "mf",
                "uhaglmmnn", "mfk", "wnymfkf", "powttkmdvv", "kwnyxmflkf", "xx", "rnqbhxsj",
                "uysqhb", "pttkmdvv", "hmmn", "iq", "m", "ymfkf", "zckgdh", "zckh", "hmm", "xuefx",
                "mv", "iqrk", "tmv", "iqk", "wnyxmfkf", "uysyqhb", "v", "m", "pwttkmdvv",
                "rnqbhsj"};
        int expected = 10;
        assertEquals(expected, longestStrChain(words));
    }
}
