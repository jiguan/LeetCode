package com.leetcode.backtrack;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, 0);
    }

    // use int 32 bits to indicate 26 letters
    private int dfs(List<String> arr, int index, int cur) {
        int res = Integer.bitCount(cur);
        if (index == arr.size()) return res;

        // order doesn't matter
        for (int i = index; i < arr.size(); ++i) {
            String str = arr.get(i);
            int str_bit = cur, dup = 0;
            for (char ch : str.toCharArray()) {
                dup |= str_bit & (1 << (ch - 'a'));
                str_bit |= 1 << (ch - 'a');
            }

            if (dup == 0) {
                // order doesn't matter
                res = Math.max(res, dfs(arr, i + 1, str_bit));
            }
        }
        return res;
    }

    /*
     *   //@formatter:off
     *  public int maxLength(List<String> arr) {
     *      return dfs(arr, 0, new HashSet<>());
     *  }
     *  
     *  private int dfs(List<String> arr, int index, Set<Character> cur) {
     *      int res = cur.size();
     *      if (index == arr.size()) return res;
     *  
     *      // order doesn't matter
     *      for (int i = index; i < arr.size(); ++i) {
     *          Set<Character> copy = new HashSet<>(cur);
     *          String str = arr.get(i);
     *          for (int j = 0; j < str.length() && copy.add(str.charAt(j)); j++) 
     *          if (str.length() + cur.size() == copy.size()) {
     *              res = Math.max(res, dfs(arr, i + 1, copy));
     *          }
     *      }
     *      return res;
     *  }
     *
     */ //@formatter:on

    @Test
    public void test0() {
        List<String> arr = Arrays.asList("un", "iq", "ue");
        int expected = 4;
        assertEquals(expected, maxLength(arr));
    }

    @Test
    public void test1() {
        List<String> arr = Arrays.asList("cha", "r", "act", "ers");
        int expected = 6;
        assertEquals(expected, maxLength(arr));
    }

    @Test
    public void test2() {
        List<String> arr = Arrays.asList("a", "abc", "d", "de", "def");
        int expected = 6;
        assertEquals(expected, maxLength(arr));
    }
}
