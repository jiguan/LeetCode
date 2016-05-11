package practice.first.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import practice.first.util.TreeNode;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i =0;i<inorder.length;i++) {
            inMap.put(inorder[i], i);
        }
        return build(0, inorder.length-1, inorder, postorder.length-1, postorder, inMap);
    }
    
    private TreeNode build(int inStart, int inEnd, int[] inorder, int postEnd, int[]postorder, Map<Integer, Integer> inMap) {
        if(inStart>inEnd||postEnd<0) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootInorderIndex = inMap.get(root.val);
        int shift = inEnd - rootInorderIndex; //right tree length
        TreeNode right = build(rootInorderIndex+1, inEnd, inorder, postEnd-1, postorder, inMap);
        TreeNode left = build(inStart, rootInorderIndex-1, inorder, postEnd-shift-1, postorder, inMap);
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
        int[] inorder = new int[]{4,10,3,1,7,11,8,2};
        int[] postorder = new int[]{4,1,3,10,11,8,2,7};
        TreeNode root = buildTree(inorder, postorder);
        prettyPrint(root);
    }
}
