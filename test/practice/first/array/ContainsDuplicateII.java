package practice.first.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ContainsDuplicateII {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<>(k);
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (!set.add(num))
				return true;
			if (i >= k) {
				set.remove(nums[i - k]);
			}
		}
		return false;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { -1, -1 };
		int k = 1;
		assertTrue(containsNearbyDuplicate(nums, k));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[] { -1,0, -1 };
		int k = 1;
		assertFalse(containsNearbyDuplicate(nums, k));
	}

}
