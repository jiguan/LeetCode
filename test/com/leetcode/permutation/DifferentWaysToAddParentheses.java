package com.leetcode.permutation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DifferentWaysToAddParentheses {
    Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input)) return map.get(input);
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> list1 = diffWaysToCompute(part1);
                List<Integer> list2 = diffWaysToCompute(part2);
                for (int num1 : list1) {
                    for (int num2 : list2) {
                        int sum = 0;
                        switch (input.charAt(i)) {
                            case '+' :
                                sum = num1 + num2;
                                break;
                            case '-' :
                                sum = num1 - num2;
                                break;
                            case '*' :
                                sum = num1 * num2;
                        }
                        res.add(sum);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        return res;
    }

    @Test
    public void test0() {
        List<Integer> expected = Arrays.asList(2, 0);
        List<Integer> actual = diffWaysToCompute("2-1-1");
        assertEquals(expected, actual);
    }
}
