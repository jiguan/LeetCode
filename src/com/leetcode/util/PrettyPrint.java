package com.leetcode.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrettyPrint {
    static public void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }

    static public void print(int[] arr) {
        System.out.println(format(arr));
    }

    static public void print(int[][] arrs) {
        for (int[] arr : arrs) {
            System.out.print("[");
            StringBuilder builder = new StringBuilder();
            for (int num : arr) {
                builder.append(", " + num);
            }
            if (builder.length() > 0) builder.delete(0, 2);
            System.out.print(builder.toString());
            System.out.println("]");
        }
    }

    static public void print(boolean[][] arrs) {
        for (boolean[] arr : arrs) {
            System.out.print("[");
            StringBuilder builder = new StringBuilder();
            for (boolean bool : arr) {
                builder.append(", " + (bool ? "Y, " : "N, "));
            }
            if (builder.length() > 0) builder.delete(0, 2);
            System.out.print(builder.toString());
            System.out.println("]");
        }
    }

    static public void print(String[] arr) {
        System.out.println(format(arr));
    }

    static public void print(char[][] chars) {
        System.out.print("[");
        for (int i = 0; i < chars.length; i++) {
            char[] arr = chars[i];
            if (i == 0) {
                System.out.print("[");
            } else {
                System.out.print(" [");
            }
            for (char c : arr) {
                System.out.print(c + " ");
            }
            if (i == chars.length - 1) {
                System.out.println("]]");
            } else {
                System.out.println("]");
            }
        }

    }

    static public <T> void print(List<T> list) {
        if (list == null) {
            System.out.println("null");
            return;
        }
        System.out.print("[");
        StringBuilder builder = new StringBuilder();
        for (T element : list) {
            builder.append(", " + element);
        }
        if (builder.length() > 0) builder.delete(0, 2);
        System.out.print(builder.toString());
        System.out.println("]");
    }

    static public void print(TreeNode root) {
        TreePrinter.printNode(root);
    }

    static public <K, V> void print(Map<K, V> map) {
        System.out.print(Arrays.toString(map.entrySet().toArray()));
    }

    public static void print(TreeLinkNode root) {
        TreeLinkNode fake = new TreeLinkNode(-1);
        fake.next = root;
        while (fake.next != null) {
            TreeLinkNode node = fake.next;
            fake.next = node.left != null ? node.left : node.right;
            while (node != null) {
                System.out.print(node.val + "->");
                node = node.next;
            }
            System.out.println("null");
        }
    }

    public static String format(int[] nums) {
        if (nums == null) return "null";
        return Arrays.stream(nums).mapToObj(Integer::toString).collect(Collectors.joining(", "));
    }

    public static String format(String[] arr) {
        if (arr == null) return "null";
        return Arrays.stream(arr).collect(Collectors.joining(", "));
    }
}
