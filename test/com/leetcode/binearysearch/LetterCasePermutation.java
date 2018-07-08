package com.leetcode.binearysearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        
        for(int i = 0; i < S.length(); i++) {
            if(Character.isAlphabetic(S.charAt(i))) {
                int size = queue.size();
                for(int j = 0; j < size; j++) {
                    char[] arr = queue.poll().toCharArray();
                    
                    arr[i] = Character.toUpperCase(arr[i]);
                    queue.offer(String.valueOf(arr));
                    arr[i] = Character.toLowerCase(arr[i]);
                    queue.offer(String.valueOf(arr));
                }
            }
        }
        return new LinkedList<>(queue);
    }
}
