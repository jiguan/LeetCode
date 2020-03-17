package com.leetcode.graph.dependency;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

/*
 * Each process only has one parent process, but may have one or more children processes. We use two
 * list of integers to represent a list of processes, where the first list contains PID for each
 * process and the second list contains the corresponding PPID. You should assume that when a
 * process is killed, all its children processes will be killed.
 */
/* Input: pid = [1, 3, 10, 5] ppid = [3, 0, 5, 3] kill = 5 Output: [5,10]
 * 
 * @formatter:off
 * Explanation: 
 *           3
 *         /   \
 *        1     5
 *             /
 *            10
 * @formatter:on
 * 
 * Kill 5 will also kill 10.
 */
public class KillProcess {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // node - children
        Map<Integer, Set<Integer>> children = new HashMap<>();

        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            int id = pid.get(i), parent = ppid.get(i);
            if (!children.containsKey(parent)) {
                children.put(parent, new HashSet<>());
            }
            children.get(parent).add(id);
        }

        List<Integer> res = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int id = queue.poll();
            res.add(id);
            if(children.containsKey(id)) {
                queue.addAll(children.get(id));
            }
        }
        return res;
    }

    @Test
    public void test0() {
        List<Integer> pid = Arrays.asList(1, 3, 10, 5);
        List<Integer> ppid = Arrays.asList(3, 0, 5, 3);
        int kill = 5;
        List<Integer> expected = Arrays.asList(5, 10);
        assertEquals(expected, killProcess(pid, ppid, kill));
    }
}
