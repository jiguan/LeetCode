package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class CompressTree {
    public int[] compressDenseTree(TreeNode root) {
        int height = getHeight(root);
        if (height == 0) {
            return new int[0];
        }
        int len = (int) Math.pow(2, height);
        // use int for each node, reduce size from 12 byte to 4 byte
        int[] heap = new int[len];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Queue<Integer> idxQueue = new LinkedList<>();
        idxQueue.offer(1);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer index = idxQueue.poll();
            heap[index] = cur.val;
            if (cur.left != null) {
                queue.offer(cur.left);
                idxQueue.offer(2 * index);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                idxQueue.offer(2 * index + 1);
            }
        }

        return heap;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }



    // use map instead of heap array
    public Map<Integer, Integer> compressSparseTree(TreeNode root) {
        // index - value
        Map<Integer, Integer> record = new HashMap<>();
        if (root == null) {
            return record;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> idxQueue = new LinkedList<>();
        queue.offer(root);
        idxQueue.offer(1);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int idx = idxQueue.poll();
            record.put(idx, cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                idxQueue.offer(2 * idx);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                idxQueue.offer(2 * idx + 1);
            }
        }
        return record;
    }

    @Test
    public void testCompressSparseTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Map<Integer, Integer> map = compressSparseTree(root);
        System.out.println(map);
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        preHelper(root, res);
        return res;
    }

    public void preHelper(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        preHelper(root.left, res);
        preHelper(root.right, res);
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inHelper(root, res);
        return res;
    }

    private void inHelper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                inHelper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                inHelper(root.right, res);
            }
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // value - index
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = build(preorder, 0, inorder, 0, inorder.length - 1, inorderMap);
        return root;
    }

    private TreeNode build(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd || preStart >= preorder.length)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inorderIndex = inorderMap.get(preorder[preStart]);

        int leftSubSize = inorderIndex - inStart;
        root.left = build(preorder, preStart + 1, inorder, inStart, inorderIndex - 1, inorderMap);
        root.right = build(preorder, preStart + leftSubSize + 1, inorder, inorderIndex + 1, inEnd,
                inorderMap);

        return root;
    }

}
