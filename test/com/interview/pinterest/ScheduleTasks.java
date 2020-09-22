package com.interview.pinterest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleTasks {

    static Set<String> stale(String[][] preTasks, Map<String, String> times) {
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Set<String>> parents = new HashMap<>();
        for (String[] task : preTasks) {
            if (!inDegree.containsKey(task[0])) {
                inDegree.put(task[0], 0);
                parents.put(task[0], new HashSet<>());
            }
            if (!inDegree.containsKey(task[1])) {
                inDegree.put(task[1], 0);
                parents.put(task[1], new HashSet<>());
            }
            inDegree.put(task[1], inDegree.get(task[1]) + 1);
            parents.get(task[1]).add(task[0]);
        }
        Deque<String> queue = new ArrayDeque<>();
        for (String task : inDegree.keySet()) {
            if (inDegree.get(task) == 0) {
                queue.offer(task);
            }
        }
        Set<String> res = new HashSet<>();
        while (!queue.isEmpty()) {
            String curTask = queue.poll();
            Set<String> parent = parents.get(curTask);
            String lastTime = times.getOrDefault(curTask, "0");
            for (String p : parent) {
                if (res.contains(p) || lastTime.compareTo(times.get(p)) < 0) {
                    res.add(curTask);
                    break;
                }
            }
            for (String task : inDegree.keySet()) {
                if (parents.get(task).contains(curTask)) {
                    inDegree.put(task, inDegree.get(task) - 1);
                    if (inDegree.get(task) == 0) {
                        queue.offer(task);
                    }
                }
            }
        }
        printSet("", res);
        return res;
    }

    static void printArray(String s, int[] array) {
        System.out.println(s);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void printSet(String s, Set<String> set) {
        System.out.println(s);
        for (String i : set) {
            System.out.println(i + " ");
        }
        System.out.println();
    }

    static void printList(String s, List<String> list) {
        System.out.println(s);
        for (String i : list) {
            System.out.println(i + " --> ");
        }
        System.out.println();
    }

    static void printListArray(String s, List<int[]> list) {
        System.out.println(s);
        for (int[] i : list) {
            printArray("", i);
        }
        System.out.println();
    }

    static void printListInt(String s, List<Integer> list) {
        System.out.println(s);
        for (Integer i : list) {
            System.out.println(i + " --> ");
        }
        System.out.println();
    }

    static void printMap(String s, Map<String, List<Integer>> map) {
        System.out.println(s);
        for (String ss : map.keySet()) {
            printListInt(ss, map.get(ss));
        }
        System.out.println();
    }

    static void printIntMap(String s, Map<Integer, Integer> map) {
        System.out.println(s);
        for (Integer i : map.keySet()) {
            System.out.println("key: " + i + " value: " + map.get(i));
        }
        System.out.println();
    }

    // Driver Code
    public static void main(String args[]) {
        String[][] imap = new String[][] {{"clean", "mapper"}, {"metadata", "statistics"},
                {"mapper", "update"}, {"update", "statistics"}, {"clean", "metadata"},
                {"mapper", "reducer"}, {"metadata", "timestamp"}};
        Map<String, String> imap2 = new HashMap<>();
        imap2.put("clean", "20170302-1129");
        imap2.put("mapper", "20170302-1155");
        imap2.put("update", "20170302-1150");
        imap2.put("statistics", "20170302-1153");
        imap2.put("metadata", "20170302-1130");
        imap2.put("reducer", "20170302-1540");
        stale(imap, imap2);
    }

}
