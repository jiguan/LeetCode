package practice.first.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate0(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // Long floor = set.floor((long)(nums[i] + t)); //less than t, but didn't set lower
            // bound
            // if (floor != null && floor + t >= nums[i]) { //check lower bound is in the range
            // return true;
            // }
            Long ceil = set.ceiling((long) (nums[i] - t));
            if (ceil != null && ceil - nums[i] <= t) {
                return true;
            }

            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }

        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0)
            return false;
        long bucketSize = t + 1; // t=2 , 0, 1, 2 should go to same bucket
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long bucketIndex = getBucketIndex(nums[i], bucketSize); // if bucet
            if (map.containsKey(bucketIndex))
                return true;
            if (map.containsKey(bucketIndex - 1) && Math.abs(map.get(bucketIndex - 1) - nums[i]) <= t)
                return true;
            if (map.containsKey(bucketIndex + 1) && Math.abs(map.get(bucketIndex + 1) - nums[i]) <= t)
                return true;
            map.put(bucketIndex, (long) nums[i]);
            if (i >= k)
                map.remove(getBucketIndex(nums[i - k], bucketSize));
        }
        return false;
    }

    private long getBucketIndex(int value, long bucketSize) {
        if (value >= 0)
            return value / bucketSize;
        else
            return (value + 1) / bucketSize - 1; // if t=4, t+1=5, [0,1,2,3,4] should go to bucket
                                                 // 0, [-1,-2,-3,-4,-5] should go to bucket -1
    }

    @Test
    public void test0() {
        int[] nums = new int[] {0};
        int k = 0;
        int t = 0;
        assertFalse(containsNearbyAlmostDuplicate(nums, k, t));
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1};
        int k = 1;
        int t = 1;
        assertFalse(containsNearbyAlmostDuplicate(nums, k, t));
    }

    @Test
    public void test2() {
        int[] nums = new int[] {-1, -1};
        int k = 1;
        int t = 0;
        assertTrue(containsNearbyAlmostDuplicate(nums, k, t));
    }

    @Test
    public void test3() {
        int[] nums = new int[] {-3, 3};
        int k = 2;
        int t = 4;
        assertFalse(containsNearbyAlmostDuplicate(nums, k, t));
    }

    @Test
    public void test4() {
        int[] nums = new int[] {0, 2147483647};
        int k = 1;
        int t = 2147483647;
        assertTrue(containsNearbyAlmostDuplicate(nums, k, t));
    }

    @Test
    public void test5() {
        int[] nums = new int[] {-1, -1};
        int k = 1;
        int t = -1;
        assertFalse(containsNearbyAlmostDuplicate(nums, k, t));
    }
}
