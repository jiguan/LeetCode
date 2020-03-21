package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RangeSumQueryMutable {

    @Test
    public void test0() {
        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);
        assertEquals(9, numArray.sumRange(0, 2));
        numArray.update(1, 2);
        assertEquals(8, numArray.sumRange(0, 2));
    }
}


// https://leetcode.com/problems/range-sum-query-mutable/discuss/75763/7ms-Java-solution-using-bottom-up-segment-tree
class NumArray {
    int[] tree;
    int n;

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        buildTree(nums);
    }

    private void buildTree(int[] nums) {
        for (int i = n; i < n * 2; i++) {
            tree[i] = nums[i - n];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    void update(int i, int val) {
        for (tree[i += n] = val; i > 0; i >>= 1) {
            tree[i >> 1] = tree[i] + tree[i ^ 1];
        }
    }

    public int sumRange(int i, int j) {
        int ret = 0;
        for (i += n, j += n; i <= j; i >>= 1, j >>= 1) {
            if ((i & 1) == 1) ret += tree[i++];
            if ((j & 1) == 0) ret += tree[j--];
        }
        return ret;
    }
}
