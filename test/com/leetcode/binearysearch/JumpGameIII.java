package com.leetcode.binearysearch;

import static org.junit.Assert.assertTrue;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (arr[index] == 0) return true;
            visited[index] = true;
            if (index + arr[index] < arr.length && visited[index + arr[index]] == false)
                queue.offer(index + arr[index]);
            if (index - arr[index] >= 0 && visited[index - arr[index]] == false)
                queue.offer(index - arr[index]);
        }
        return false;
    }

    @Test
    public void test0() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        assertTrue(canReach(arr, start));
    }

}
