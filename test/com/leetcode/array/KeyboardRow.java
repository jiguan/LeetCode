package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class KeyboardRow {

	public String[] findWords(String[] words) {
		if(words.length==0) return words;
        String[] strs = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<strs.length;++i) {
        	String str = strs[i];
            for(char c : str.toCharArray()) {
            	map.put(c, i);
            }
        }
        
        List<String> result = new LinkedList<>();
        for(int i=0;i<words.length;++i) {
        	String word = words[i].toUpperCase();
        	int index = map.get(word.charAt(0));
        	for(int j=1;j<word.length();++j) {
        		if(map.get(word.charAt(j)) != index) {
        			index = -1;
        			break;
        		}
        	}
        	if(index!=-1) result.add(words[i]);
        }
        return result.toArray(new String[0]);
    }
	
	
	@Test
	public void test0() {
		String[] input = {"Hello", "Alaska", "Dad", "Peace"};
		String[] output = {"Alaska", "Dad"};
		
		assertArrayEquals(output, findWords(input));
	}
}
