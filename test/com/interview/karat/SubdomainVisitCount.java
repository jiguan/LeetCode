package com.interview.karat;

import java.util.HashMap;
import java.util.Map;

public class SubdomainVisitCount {
    public Map<String, Integer> subdomainVisits(String[] records) {
        // O(n)
        Map<String, Integer> map = new HashMap<>();
        if (records == null) return map;
        // O(n)
        for (String record : records) {
            int i = record.indexOf(',');
            int n = Integer.valueOf(record.substring(0, i));
            String url = record.substring(i + 1);

            map.put(url, map.getOrDefault(url, 0) + n);
            // O(1)
            for (int j = url.length() - 1; j >= 0; --j) {
                if (url.charAt(j) == '.') {
                    String sub = url.substring(j + 1);
                    map.put(sub, map.getOrDefault(sub, 0) + n);
                }
            }
        }

        return map;
    }
}
