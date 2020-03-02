package com.leetcode.topological;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> depth = new HashMap<>();
        String res = "";
        for (String word : words) {
            for (char c : word.toCharArray()) {
                depth.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; ++i) {
            String curr = words[i];
            String next = words[i + 1];
            int length = Math.min(curr.length(), next.length());
            for (int j = 0; j < length; ++j) {
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);

                if (c1 != c2) {
                    if (!map.containsKey(c1)) {
                        map.put(c1, new HashSet<>());
                    }
                    if (!map.get(c1).contains(c2)) {
                        map.get(c1).add(c2);
                        depth.put(c2, depth.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char c1 : depth.keySet()) {
            if (depth.get(c1) == 0) {
                queue.add(c1);
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            res += c;
            if (map.containsKey(c)) {
                for (char c2 : map.get(c)) {
                    depth.put(c2, depth.get(c2) - 1);
                    if (depth.get(c2) == 0) {
                        queue.add(c2);
                    }
                }
            }
        }

        // depth.size == all unique chars
        if (res.length() != depth.size()) return "";
        return res;
    }


    @Test
    public void test0() {
        String[] words = new String[] {"wrt", "wrf", "er", "ett", "rftt"};
        String expected = "wertf";
        assertEquals(expected, alienOrder(words));
    }
    
    @Test
    public void test1() {
        String[] words = new String[] {"z", "x", "z"};
        String expected = "";
        assertEquals(expected, alienOrder(words));
    }
    
    @Test
    public void test2() {
        String[] words = new String[] {"ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby"};
        String expected = "";
        assertEquals(expected, alienOrder(words));
    }
}
