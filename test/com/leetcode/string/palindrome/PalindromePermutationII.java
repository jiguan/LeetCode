package com.leetcode.string.palindrome;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();
        int[] count = new int[256];
        int odds = 0;
        for (char c : s.toCharArray())
            count[c]++;
        for (int c : count)
            if (c % 2 != 0)
                odds++;
        if (odds <= 1) {
            Character center = null;
            for (int idx = 0; idx < count.length; idx++) {
                if (count[idx] % 2 == 1) {
                    center = ((char) idx);
                    count[idx]--;
                    break;
                }
            }
            generate(ans, count, (center != null ? String.valueOf(center) : new String()),
                    s.length());
        }
        return ans;
    }

    private void generate(List<String> ans, int[] count, String build, int len) {
        for (int idx = 0; idx < count.length; idx++) {
            if (count[idx] > 0) {
                count[idx] -= 2;
                generate(ans, count, ((char) idx) + build + ((char) idx), len);
                count[idx] += 2;
            }
        }
        if (build.length() == len)
            ans.add(new String(build));
    }

    @Test
    public void test0() {
        String s = "aabb";
        List<String> expected = Arrays.asList("abba", "baab");
        assertEquals(expected, generatePalindromes(s));
    }
}
