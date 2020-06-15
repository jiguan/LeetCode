package com.interview.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Assuming a mapping for morse code to char (and vice versa) is available, write two functions:
 * 
 * encode: Encodes a regular string into morse code string. decode: Returns all possible decoded
 * message strings for a given morse code string. Hypothetical mapping and examples:
 * 
 * { 'A': '.', 'B': '-', 'C': '.-'}
 * 
 * encode('AB') -> '.-' encode('C') -> '.-' decode('.-') -> ['C', 'AB']
 */
public class MorseCode {
    Map<String, String> map1 = new HashMap<>();
    Map<String, String> map2 = new HashMap<>();

    public void init() {
        String[] arr1 = {"A", "B", "C"};
        String[] arr2 = {".", "-", ".-"};
        for (int i = 0; i < arr1.length; i++) {
            map1.put(arr1[i], arr2[i]);
            map2.put(arr2[i], arr1[i]);
        }
    }

    public String encode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(map1.get(c + ""));
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, 0, new StringBuilder());
        return res;
    }

    private void dfs(String s, List<String> res, int cur, StringBuilder sb) {
        if (cur == s.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = cur; i < s.length(); i++) {
            String next = s.substring(cur, i + 1);
            if (map2.containsKey(next)) {
                sb.append(map2.get(next));
                dfs(s, res, i + 1, sb);
                sb.delete(sb.length() - map2.get(next).length(), sb.length());
            }
        }
    }

    private List<String> decodeDp(String s) {
        List<String>[] dp = new List[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = new ArrayList<>();
            String sub = s.substring(0, i + 1);
            if (map2.containsKey(sub)) {
                dp[i].add(map2.get(sub));
            }
            for (int j = 0; j < i; j++) {
                sub = s.substring(j + 1, i + 1);
                if (map2.containsKey(sub)) {
                    for (String ss : dp[j]) {
                        dp[i].add(ss + map2.get(sub));
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }
}
