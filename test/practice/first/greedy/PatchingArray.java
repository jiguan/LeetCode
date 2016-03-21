package practice.first.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatchingArray {
	public int minPatches0(int[] nums, int n) {
		if (n <= 0)
			return 0;
		int index = 0, sum = 0, target = 1;
		int patchNum = 0;
		while (target <= n) {
			if (index < nums.length && nums[index] <= target) {
				sum += nums[index++];
				target = sum + 1;
			} else {
				patchNum++;
				sum += target;
				target += 1;
			}
		}
		return patchNum;
	}

	public int minPatches(int[] nums, int n) {
		if (n <= 0) return 0;
		long sum = 0;
		int i = 0, patchNum = 0;
		while (sum < n) {
			if (i < nums.length && nums[i] <= (sum + 1)) {
				sum += nums[i++];
			} else {
				patchNum++;
				sum = sum + sum + 1;
			}
		}
		return patchNum;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 2, 3 };
		int n = 7;
		assertEquals(2, minPatches(nums, n));
	}
}
