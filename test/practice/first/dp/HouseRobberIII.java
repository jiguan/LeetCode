package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import practice.first.util.TreeNode;

public class HouseRobberIII {
    
    public int rob(TreeNode root) {
        return Math.max(robSub(root)[0] , robSub(root)[1]);
    }
    
    private int[] robSub(TreeNode node) {
        int[] res = new int[2];
        if(node==null) return res;
        int[] left = robSub(node.left);
        int[] right = robSub(node.right);
        //rob this
        res[0]= node.val + left[1] + right[1];
        //rob next
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
    
    @Test
    public void test0() {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        left.right = new TreeNode(3);
        TreeNode right = new TreeNode(3);
        right.right = new TreeNode(1);
        root.left = left;
        root.right = right;
        assertEquals(7, rob(root));
    }
    
    @Test
    public void test1() {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        left.right = new TreeNode(4);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        assertEquals(7, rob(root));
    }
    
   
}
