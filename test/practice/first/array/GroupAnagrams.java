package practice.first.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.junit.Test;

import practice.first.util.PrettyPrint;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length==0) return res;
        Map<Integer, PriorityQueue<String>> map = new HashMap<>();
        for(String str : strs) {
            map.computeIfAbsent(getCode(str), k -> new PriorityQueue<>()).add(str);
        }
        for(PriorityQueue<String> queue : map.values()) {
            res.add(new ArrayList<String>(queue));
        }
        return res;
    }
    
    private int getCode(String str) {
        int code = 0;
        for(char c : str.toCharArray()) {
            code += c-'a';
        }
        return code;
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
}
