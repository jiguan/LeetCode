package com.interview.oa.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the
 * unique id and the second integer represents a value. Your task is to find an element from a and
 * an element form b such that the sum of their values is less or equal to target and as close to
 * target as possible. Return a list of ids of selected elements. If no pair is possible, return an
 * empty list.
 */
/*
 * @formatter:off
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 * 
 * Output: [[2, 1]]
 * 
 * Explanation:
 * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
 * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 * @formatter:on
 */
public class OptimalUtilization {
    public List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
        Collections.sort(a, (i, j) -> i[1] - j[1]);
        Collections.sort(b, (i, j) -> i[1] - j[1]);
        List<int[]> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int m = a.size();
        int n = b.size();
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            int sum = a.get(i)[1] + b.get(j)[1];
            if (sum > target) {
                --j;
            } else {
                // sum <= target
                if (max <= sum) {
                    // sum is closer to target
                    if (sum > max) {
                        max = sum;
                        result.clear();
                    }
                    result.add(new int[] {a.get(i)[0], b.get(j)[0]});
                    int index = j - 1;
                    while (index >= 0 && b.get(index)[1] == b.get(index + 1)[1]) {
                        result.add(new int[] {a.get(i)[0], b.get(index--)[0]});
                    }
                }
                ++i;
            }
        }
        return result;
    }
}
