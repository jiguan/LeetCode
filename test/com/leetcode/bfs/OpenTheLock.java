package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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
            // make sure begin is smaller
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

    public int openLock0(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int t = 0; t < size; ++t) {
                String curr = queue.poll();
                if (dead.contains(curr)) continue;
                if (curr.equals(target)) return steps;

                for (int i = 0; i < 4; ++i) {
                    String next = curr.substring(0, i) + (curr.charAt(i) - '0' + 1) % 10
                            + curr.substring(i + 1);
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                    next = curr.substring(0, i) + (curr.charAt(i) - '0' + 9) % 10
                            + curr.substring(i + 1);
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            steps++;
        }

        return -1;

    }

    @Test
    public void test0() {
        String[] deadends = new String[] {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        assertEquals(6, openLock(deadends, target));
    }

    @Test
    public void test1() {
        String[] deadends = new String[] {"0000"};
        String target = "8888";
        assertEquals(-1, openLock(deadends, target));
    }
}
