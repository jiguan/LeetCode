package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();    	
		for(String str : strs) {
    		int[] arr = new int[26];
    		for(char c : str.toCharArray()) {
    			arr[c -'a']++;
    		}
    		String key = Arrays.toString(arr);
    		map.computeIfAbsent(key, k->new ArrayList<String>()).add(str);
    	}
    	return new ArrayList<>(map.values());
    }
    
    @Test
    public void test0() {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        for(List<String> list : result) {
            for(String s : list) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
    
    @Test
    public void test1() {
        String[] strs = new String[]{"ac", "bb"};
        List<List<String>> result = groupAnagrams(strs);
        for(List<String> list : result) {
            for(String s : list) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}
