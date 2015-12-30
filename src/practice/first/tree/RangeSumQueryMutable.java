package practice.first.tree;

public class RangeSumQueryMutable {
	public static void main(String[] args) {
		int[] nums = new int[] { 1, 3, 5, 7, 9, 11 };
		NumArray numArray = new NumArray(nums);
		for(int i: numArray.tree) {
			System.out.print(i+" ");
		}
	}
}

class NumArray {
	public int[] tree;
	private int[] nums;

	public NumArray(int[] nums) {
		this.nums = nums;
		tree = new int[2*nums.length-1];
		fillTree(0, nums.length-1, 0);
	}
	
	private int fillTree(int start, int end, int index) {
		if(start==end) {
			tree[index] = nums[start];
		} else {
			int mid = start + (end-start) /2;
			tree[index] = fillTree(start, mid, 2*index+1) + fillTree(mid+1, end, 2*index+2);
		}
		return tree[index];
	}
	
	void update(int i, int val) {
	}

	public int sumRange(int i, int j) {
		return 1;
	}
}