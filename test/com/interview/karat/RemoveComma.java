package com.interview.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class RemoveComma {
    public List<String> removeComma(List<String> strs) {
        List<String> res = new ArrayList<>();
        if (strs == null || strs.size() == 0) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            String[] s = str.split(",");
            addStrToMap(map, Integer.valueOf(s[0]), s[1]);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String temp = entry.getValue() + " " + entry.getKey();
            res.add(temp);
            System.out.println(temp);
        }
        return res;
    }

    private void addStrToMap(Map<String, Integer> map, int count, String s) {
        map.put(s, count + map.getOrDefault(s, 0));

        int dotIndex = s.indexOf(".");
        while (dotIndex != -1) {
            s = s.substring(dotIndex + 1);
            map.put(s, count + map.getOrDefault(s, 0));

            dotIndex = s.indexOf(".");
        }
    }
    
    @Test
    public void test0() {
        System.out.println(removeComma(new ArrayList<String>(Arrays.asList(
                "200,google.com",
                "50,english.org",
                "5,my.english.org"
            ))));
    }
}
