package com.interview.pinintest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCommonHistory {

    static Map<String, Integer> count(Map<String, Integer> ori) {
        Map<String, Integer> subCount = new HashMap<>();
        for (String domain : ori.keySet()) {
            int num = ori.get(domain);
            subCount.put(domain, subCount.getOrDefault(domain, 0) + num);
            int index = domain.indexOf('.');
            while (index >= 0) {
                domain = domain.substring(index + 1);
                subCount.put(domain, subCount.getOrDefault(domain, 0) + num);
                index = domain.indexOf('.');
            }
        }
        printMap("domain counts: ", subCount);
        return subCount;
    }

    static List<String> longestCommonHistory(String[] user1, String[] user2) {
        int len1 = user1.length, len2 = user2.length;
        int[][] dp = new int[2][len2 + 1];
        int current = 0, maxLen = 0, end = 0;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    dp[current][j] = 0;
                } else if (user1[i - 1].equals(user2[j - 1])) {
                    dp[current][j] = dp[1 - current][j - 1] + 1;
                    if (dp[current][j] > maxLen) {
                        maxLen = dp[current][j];
                        end = j;
                    }
                } else {
                    dp[current][j] = 0;
                }
            }
            current = 1 - current;
        }
        List<String> res = new ArrayList<>();
        while (maxLen-- > 0) {
            res.add(0, user2[--end]);
        }
        printList("history: ", res);
        return res;
    }

    static void printList(String s, List<String> list) {
        System.out.println(s);
        for (String i : list) {
            System.out.println(i + " --> ");
        }
        System.out.println();
    }

    static void printMap(String s, Map<String, Integer> map) {
        System.out.println(s);
        for (String ss : map.keySet()) {
            System.out.println(ss + " --> " + map.get(ss));
        }
        System.out.println();
    }

    // Driver Code
    public static void main(String args[]) {
        Map<String, Integer> map = new HashMap<>();
        map.put("google.com", 60);
        map.put("yahoo.com", 50);
        map.put("sports.yahoo.com", 80);

        count(map);
        String[] user11 = new String[] {"3234.html", "xys.html", "7hsaa.html"};
        String[] user22 = new String[] {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        String[] user0 =
                new String[] {"/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html"};
        String[] user1 =
                new String[] {"/one.html", "/two.html", "/three.html", "/four.html", "/six.html"};
        String[] user2 = new String[] {"/nine.html", "/two.html", "/three.html", "/four.html",
                "/six.html", "/seven.html"};
        String[] user3 = new String[] {"/three.html", "/eight.html"};
        longestCommonHistory(user11, user22);
        longestCommonHistory(user0, user2);
        longestCommonHistory(user1, user2);
        longestCommonHistory(user0, user3);
        longestCommonHistory(user1, user3);
    }
}
