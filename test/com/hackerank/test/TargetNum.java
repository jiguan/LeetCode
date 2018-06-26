package com.hackerank.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

/* 
 * Given an array, like [4,1,3,2] and a target. Write a function to check: whether we can get the target by the numbers in the array, available operates are: +, *, ^ ().
Keep the array order
 */
public class TargetNum {
    char[] operators = new char[]{'+', '*', '^'};
    Map<String, Integer> map = new HashMap<>();
    List<String> results = new LinkedList<>();

    public List<String> getExpression(int[] nums, int target) {
        compute(nums, 0, nums.length - 1, target);
        return results;
    }

    private List<String> compute(int[] nums, int start, int end, int target) {
        if (start == end) {
            int val = nums[start];
            map.put(String.valueOf(val), val);
            return Arrays.asList(String.valueOf(val));
        }

        List<String> res = new LinkedList<>();
        for (int i = start; i < end; i++) {
            List<String> part1 = compute(nums, start, i, target);
            List<String> part2 = compute(nums, i + 1, end, target);

            for (String expr1 : part1) {
                for (String expr2 : part2) {
                    int num1 = map.get(expr1), num2 = map.get(expr2);
                    for (char op : operators) {
                        int tmp = 0;
                        switch (op) {
                            case '+' :
                                tmp = num1 + num2;
                                break;
                            case '*' :
                                tmp = num1 * num2;
                                break;
                            case '^' :
                                tmp = num1 ^ num2;
                                break;
                        }
                        String expression = expr1 + op + expr2;
                        if (tmp == target) results.add(expression);
                        expression = "(" + expression + ")";
                        res.add(expression);
                        map.put(expression, tmp);
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{4, 1, 3, 2};
        int target = 32;
        PrettyPrint.print(getExpression(nums, target));
    }
}
