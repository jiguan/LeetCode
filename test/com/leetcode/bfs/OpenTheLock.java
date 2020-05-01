package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000");
        end.add(target);
        int res = 0;

        while (!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
                Set<String> tmp = begin;
                begin = end;
                end = tmp;
            }

            Set<String> tmp = new HashSet<>();
            for (String s : begin) {
                if (end.contains(s)) return res;
                if (deads.contains(s)) continue;
                deads.add(s);

                for (int i = 0; i < 4; i++) {
                    char c = s.charAt(i);
                    String s1 = s.substring(0, i) + ((c - '0' + 1) % 10) + s.substring(i + 1);
                    if (!deads.contains(s1)) tmp.add(s1);

                    String s2 = s.substring(0, i) + ((c - '0' + 9) % 10) + s.substring(i + 1);
                    if (!deads.contains(s2)) tmp.add(s2);
                }
            }
            res++;
            begin = tmp;
        }
        return -1;
    }

    @Test
    public void test0() {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        assertEquals(6, openLock(deadends, target));
    }
}
