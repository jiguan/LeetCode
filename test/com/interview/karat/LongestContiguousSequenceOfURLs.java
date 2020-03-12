package com.interview.karat;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class LongestContiguousSequenceOfURLs {
    // We have some clickstream data that we gathered on our client's website. Using cookies, we
    // collected snippets of users' anonymized URL histories while they browsed the site. The
    // histories are in chronological order and no URL was visited more than once per person.

    // Write a function that takes two user¡¯s browsing histories as input and returns the longest
    // contiguous sequence of URLs that appears in both.

    // Sample input:

    // user0 = [ "/start.html", "/pink.php", "/register.asp", "/orange.html", "/red.html" ].
    // 1point3acres
    // user1 = [ "/red.html", "/green.html", "/blue.html", "/pink.php", "/register.asp" ]
    // user2 = [ "/start.html", "/green.html", "/blue.html", "/pink.php", "/register.asp",
    // "/orange.html" ]
    // user3 = [ "/blue.html", "/logout.php" ]

    // Sample output:

    // f(user0, user2):
    // /pink.php
    // /register.asp
    // /orange.html

    // f(user1, user2):
    // /green.html
    // /blue.html
    // /pink.php
    // /register.asp

    // f(user0, user3):
    // (empty)

    // f(user1, user3):
    // /blue.html
    public List<String> search(String[] s, String[] t) {
        int m = s.length, n = t.length;
        int[][] dp = new int[m + 1][n + 1];
        // indicate where it ends in String s
        int endingIndex = -1, length = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[i] == t[j]) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                    if (dp[i + 1][j + 1] > length) {
                        endingIndex = i;
                        length = dp[i + 1][j + 1];
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        if(length == 0) return res;
        while (length-- > 0) {
            res.add(0, s[endingIndex--]);
        }
        return res;
    }

    @Test
    public void test0() {
        String[] s = new String[] {"/start.html", "/pink.php", "/register.asp", "/orange.html", "/red.html"};
        String[] t = new String[] {"/start.html", "/green.html", "/blue.html", "/pink.php", "/register.asp", "/orange.html"};
        List<String> expected =
                Arrays.asList("/pink.php", "/register.asp", "/orange.html");
        assertEquals(expected, search(s, t));
    }

}
