package com.leetcode.string;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class PalindromePairs {
    /* 3 cases: 
     * case 0: word1 is "" and word2 is palindrome
     * case 1: word1 is reversed word2
     * case 2: (0, j) of word1 is palindrome, and word2 equals word1's (j, end), word2 + word1 is Palindrome
     * case 3: (j, end) of word1 is Palindrome, and word2 equals word1's (0, j), word1 + word2 is Palindrome
     */
    
    
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>(words.length); //word - index
        for(int i=0;i<words.length;i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> result = new LinkedList<>();
        for(int i=0;i<words.length;i++) {
            String word = words[i];
            //if we don't make j reach length, str1 cannot be the whole word
            for(int j=0;j<=word.length();j++) {
                String str1 = word.substring(0,j);
                String str2 = word.substring(j);
                //if str1 is empty, str2 is word and if reversed str2 is in map, fall into case 1, should add i, reversed str2 
                //// if str1 is not empty and palindrome, like ab, and str2 is cd, and there is a reversed str2 in map, "dc", then dc abcd is one result
                if(isPalindrome(str1)) {
                    String reversedStr2 = new StringBuilder(str2).reverse().toString();
                    if(map.containsKey(reversedStr2)&&map.get(reversedStr2)!=i) {
                        result.add(Arrays.asList(map.get(reversedStr2), i));
                    }
                }
                //str2 is empty and str1 is the word, it is handled when j becomes the length
                if(isPalindrome(str2)&&!str2.isEmpty()) {
                    String reversedStr1 = new StringBuilder(str1).reverse().toString();
                    if(map.containsKey(reversedStr1)&&map.get(reversedStr1)!=i) {
                        result.add(Arrays.asList(i, map.get(reversedStr1)));
                    }
                }
            }
        }
        return result;
    }
    
    private boolean isPalindrome(String word) {
        int i =0, j = word.length()-1;
        while(i<=j) {
            if(word.charAt(i)!=word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    @Test
    public void test0() {
        String[] words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
        List<List<Integer>> expected = new LinkedList<>();
        expected.add(Arrays.asList(0, 1));
        expected.add(Arrays.asList(1, 0));
        expected.add(Arrays.asList(3, 2));
        expected.add(Arrays.asList(2, 4));
        List<List<Integer>> result = palindromePairs(words);
        //[[0, 1], [1, 0], [3, 2], [2, 4]]
        for(List<Integer> list : result) {
            PrettyPrint.print(list);
        }
        
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    @Test
    public void test1() {
        String[] words = new String[]{"a", ""};
        List<List<Integer>> expected = new LinkedList<>();
        expected.add(Arrays.asList(0, 1));
        expected.add(Arrays.asList(1, 0));
        List<List<Integer>> result = palindromePairs(words);
        //[[0, 1], [1, 0]
        for(List<Integer> list : result) {
            PrettyPrint.print(list);
        }
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
}
