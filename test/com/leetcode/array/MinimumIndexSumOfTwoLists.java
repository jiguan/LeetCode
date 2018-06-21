package com.leetcode.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list2.length; ++i) {
            map.put(list2[i], i);
        }

        int max = list1.length + list2.length;
        List<String> res = new LinkedList<>();
        for (int i = 0; i < list1.length; ++i) {
            Integer j = map.get(list1[i]);
            if (j != null && i + j <= max) {
                if (i + j < max) {
                    res.clear();
                    max = i + j;
                }
                res.add(list1[i]);
            }
        }
        return res.toArray(new String[0]);
    }

    @Test
    public void test0() {
        String[] list1 = new String[] { "Shogun", "Tapioca Express", "Burger King", "KFC" };
        String[] list2 = new String[] { "KFC", "Shogun", "Burger King" };

        Assert.assertArrayEquals(new String[] { "Shogun" }, findRestaurant(list1, list2));
    }
}
