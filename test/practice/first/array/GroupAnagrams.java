package practice.first.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
    	Map<String, ArrayList<String>> map = new HashMap<>();
    	for(String str : strs) {
    		char[] chars = str.toCharArray();
    		Arrays.sort(chars);
    		map.computeIfAbsent(new String(chars), k->new ArrayList<String>()).add(str);
    	}
    	List<List<String>> res = new ArrayList<>();
    	for(ArrayList<String> list : map.values()) {
    		Collections.sort(list);
    		res.add(list);
    	}
    	return res;
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
