package com.interview.pinterest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MeetingRoom {

    static boolean rooms(int[][] schedules, int[] target) {
        List<int[]> list = new ArrayList<int[]>();
        for (int[] schedule : schedules) {
            list.add(schedule);
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        for (int i = 0; i < list.size(); i++) {
            int[] temp = list.get(i);
            if (temp[0] >= target[1]) {
                if (i == 0) {
                    return true;
                }
                int[] prev = list.get(i - 1);
                if (target[0] >= prev[1]) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        int[] last = list.get(list.size() - 1);
        if (target[0] >= last[1]) {
            return true;
        }
        return false;
    }

    static List<int[]> available(int[][] schedules) {
        List<int[]> list = new ArrayList<int[]>();
        List<int[]> res = new ArrayList<int[]>();
        for (int[] schedule : schedules) {
            list.add(schedule);
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        for (int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            int[] prev = list.get(i - 1);
            if (prev[1] < cur[0]) {
                res.add(new int[] {prev[1], cur[0]});
            }
        }
        int[] first = list.get(0);
        if (first[0] > 0) {
            res.add(0, new int[] {0, first[0]});
        }
        printListArray("available: ", res);
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

    // Driver Code
    public static void main(String args[]) {
        int[][] schedules = new int[][] {{1300, 1500}, {930, 1200}, {830, 845}};
        int[] target1 = new int[] {820, 830};
        int[] target2 = new int[] {1450, 1500};
        System.out.println(rooms(schedules, target1));
        System.out.println(rooms(schedules, target2));
        available(schedules);
    }
}
