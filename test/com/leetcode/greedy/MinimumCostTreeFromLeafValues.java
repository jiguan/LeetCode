package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MinimumCostTreeFromLeafValues {
    /*
     * 1. Pick up the leaf node with minimum value.
     * 
     * 2. Combine it with its in-order neighbor which has smaller value between neighbors.
     * 
     * 3. Once we get the new generated non-leaf node, the node with minimum value is useless (For
     * the new generated subtree will be represented with the largest leaf node value.)
     * 
     * 4. Repeat it until there is only one node.
     */
    public int mctFromLeafValues(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        int res = 0;
        while (list.size() > 1) {
            int mid = 0;
            // remove the min element
            for (int i = 1; i < list.size(); ++i) {
                if (list.get(i) < list.get(mid)) {
                    mid = i;
                }
            }
            int left = mid - 1, right = mid + 1;
            if (left < 0) {
                res += list.get(mid) * list.get(right);
            } else if (right == list.size()) {
                res += list.get(mid) * list.get(left);
            } else {
                // min product, determine to form (left, mid), right or left, (mid, right)
                res += list.get(mid) * Math.min(list.get(left), list.get(right));
            }
            list.remove(mid);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] arr = {6, 2, 4};
        int expected = 32;
        assertEquals(expected, mctFromLeafValues(arr));
    }
}
