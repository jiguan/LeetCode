package com.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i =0;i<inorder.length;i++) {
            inMap.put(inorder[i], i);
        }
        return find(0, preorder, 0, inorder.length-1, inorder, inMap);
    }
    
    private TreeNode find(int preStart, int[] preorder, int inStart, int inEnd, int[] inorder, Map<Integer, Integer> inMap) {
        if(preStart>preorder.length-1 || inStart>inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootInorderIndex = inMap.get(root.val);
        int shift = rootInorderIndex - inStart;
        TreeNode left = find(preStart+1, preorder, inStart, rootInorderIndex-1, inorder, inMap);
        TreeNode right = find(preStart+shift+1, preorder, rootInorderIndex+1, inEnd, inorder, inMap);
        root.left = left;
        root.right = right;
        return root;
    }
    
    private void prettyPrint(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Queue<TreeNode> backup = new LinkedList<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val+" ");
            if(node.left!=null) backup.add(node.left);
            if(node.right!=null) backup.add(node.right);
            if(queue.isEmpty()) {
                Queue<TreeNode> tmp = queue;
                queue = backup;
                backup = tmp;
                System.out.println();
            }
        }
        System.out.println();
    }
    
    @Test
    public void test0() {
        int[] preorder = new int[]{7,10,4,3,1,2,8,11};
        int[] inorder = new int[]{4,10,3,1,7,11,8,2};
        TreeNode root = buildTree(preorder, inorder);
        prettyPrint(root);
    }
}
