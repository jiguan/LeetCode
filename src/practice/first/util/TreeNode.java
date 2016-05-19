package practice.first.util;

import java.util.Arrays;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int x) {
		val = x;
	}
	
	@Override
	public String toString() {
		return String.format("TreeNode(%d)", val);
	}
	
	public static TreeNode build(int[] nums) {
	    Arrays.sort(nums);
	    return build(0, nums.length-1, nums);
	}
	
	private static TreeNode build(int start, int end, int[] nums) {
	    int mid = (start + end + 1) /2;
	    TreeNode root = new TreeNode(nums[mid]);
	    if(mid-1>=start) {
	        root.left = build(start, mid-1, nums);
	    }
	    if(mid+1<=end) {
	        root.right = build(mid+1, end, nums);
	    }
	    return root;
	}
	
}
