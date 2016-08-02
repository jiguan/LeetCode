package com.leetcode.math;

import java.util.LinkedList;
import java.util.List;

/*
 * Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5,
 * Return
 * 
 * [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
 * 
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> current = new LinkedList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    current.add(1);
                } else {
                    List<Integer> list = result.get(i - 1);
                    current.add(list.get(j - 1) + list.get(j));
                }
            }
            result.add(current);
        }
        return result;
    }
}
