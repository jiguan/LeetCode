package com.leetcode.hard;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Test;

/**
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In
 * each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 * 
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower
 * will open in that day.
 * 
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x
 * will be in the range from 1 to N.
 * 
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also
 * the number of flowers between them is k and these flowers are not blooming.
 * 
 * If there isn't such day, output -1.
 * 
 * e.g. flowers: [1,3,2], day1: flower1, day2:flower3, day3:flower2
 *
 */
public class KEmptySlots {
    public int kEmptySlots0(int[] flowers, int k) {
        int n = flowers.length;
        if (n == 0 || n <= k) return -1;

        int[] days = new int[n];
        for (int i = 0; i < n; ++i) {
            // flower - day
            days[flowers[i] - 1] = i + 1;
        }

        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        // i is flower index
        for (int i = 0; right < n; i++) {
            if (days[i] < days[left] || days[i] <= days[right]) {
                // check if there is a slot between left and right is earlier, if not update res
                if (i == right) {
                    res = Math.min(res, Math.max(days[left], days[right]));
                }
                left = i;
                right = i + k + 1;
            }
        }

        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    public int kEmptySlots1(int[] flowers, int k) {
        int n = flowers.length;
        if (n == 0 || n <= k) return -1;
        // k = 1, 0, [k], 2; diff is 2
        k++;
        // get upper bound
        int bucket = (n - 1) / k + 1;
        int[] lows = new int[bucket];
        int[] highs = new int[bucket];

        for (int i = 0; i < bucket; ++i) {
            lows[i] = Integer.MAX_VALUE;
            highs[i] = Integer.MIN_VALUE;
        }

        for (int day = 0; day < n; ++day) {
            int flower = flowers[day];
            int p = flower / k;
            if (flower < lows[p]) {
                lows[p] = flower;
                // find a new lowest, check left bucket
                if (p > 0 && highs[p - 1] == flower - k) {
                    return day + 1;
                }
            }
            if (flower > highs[p]) {
                highs[p] = flower;
                if (p < bucket - 1 && lows[p + 1] == flower + k) return day + 1;
            }
        }
        return -1;
    }

    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        if (n == 0 || n <= k) return -1;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            int flower = flowers[i];
            set.add(flower);

            Integer prev = set.lower(flower);
            if (prev != null && flower - prev == k + 1) return i + 1;

            Integer next = set.higher(flower);
            if (next != null && next - flower == k + 1) return i + 1;
        }
        return -1;
    }

    @Test
    public void test0() {
        int[] flowers = {1, 2, 3};
        int k = 1;
        assertEquals(-1, kEmptySlots(flowers, k));
    }

    @Test
    public void test1() {
        int[] flowers = {1, 3, 2};
        int k = 1;
        assertEquals(2, kEmptySlots(flowers, k));
    }
}
