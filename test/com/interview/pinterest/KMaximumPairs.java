package com.interview.pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class KMaximumPairs {

    static class wrap {
        int sum;
        int indexA;
        int indexB;

        public wrap(int s, int a, int b) {
            sum = s;
            indexA = a;
            indexB = b;
        }
    }

    static List<int[]> pair(int[] A, int[] B, int K) {
        PriorityQueue<wrap> pq = new PriorityQueue<>(new Comparator<wrap>() {
            @Override
            public int compare(wrap w1, wrap w2) {
                return w2.sum - w1.sum;
            }
        });
        Arrays.sort(A);
        Arrays.sort(B);
        reverse(A);
        reverse(B);

        pq.offer(new wrap(A[0] + B[0], 0, 0));
        List<int[]> res = new ArrayList<>();
        while (res.size() < K) {
            wrap w = pq.poll();
            int a = w.indexA, b = w.indexB;
            res.add(new int[] {A[a], B[b]});
            if (a < A.length - 1)
                pq.offer(new wrap(A[a + 1] + B[b], a + 1, b));
            if (b < B.length - 1)
                pq.offer(new wrap(A[a] + B[b + 1], a, b + 1));
        }
        printListArray("", res);
        return res;
    }

    static void reverse(int[] input) {
        int len = input.length;
        for (int i = 0; i < len / 2; i++) {
            int temp = input[i];
            input[i] = input[len - 1 - i];
            input[len - 1 - i] = temp;
        }
    }

    static void printListArray(String s, List<int[]> list) {
        System.out.println(s);
        for (int[] i : list) {
            printArray("", i);
        }
        System.out.println();
    }

    static void printArray(String s, int[] array) {
        System.out.println(s);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int A[] = {2, 3, -1, 0, 5};
        int B[] = {1, 4, 2};
        int K = 5;

        pair(A, B, K);

    }
}

// This code is contributed by Gitanjali.
