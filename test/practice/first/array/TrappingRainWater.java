package practice.first.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrappingRainWater {
	public int trap(int[] height) {
		int left = 0, right = height.length - 1;
		int leftmost = 0, rightmost = 0;
		int res = 0;
		while (left < right) {
			//the highest boundaries
			leftmost = Math.max(leftmost, height[left]);
			rightmost = Math.max(rightmost, height[right]);
			//some space of rightmost is wasted
			if (leftmost < rightmost) {
				//the diff is water can be trapped at left position
				res += leftmost - height[left++];
			} else {
				res += rightmost - height[right--];
			}
		}
		return res;
	}

	@Test
	public void test0() {
		int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		assertEquals(6, trap(height));
	}
}
