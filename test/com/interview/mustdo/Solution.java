package com.interview.mustdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Solution {
    
  

}

class ImpProcessor {

    // news-user, timestamp
    Map<String, List<Integer>> map = new HashMap<>();

    public void parse(String[][] inputs) {
        // input[0] = news-user
        // input[1] = timestamp
        for (String[] input : inputs) {
            parse(input);
        }

        Map<String, Integer> counts = new HashMap<>();
        // news-user
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String[] news_user = entry.getKey().split("-");
            String news = news_user[0];
            // news1-user1 => news1 - 0
            // news1-user2 => news1 - 1
            // news1-user1 => news1 - 2

            counts.put(news, counts.getOrDefault(news, 0) + 1);
        }

        // news - count
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    // news-user, timestamp
    public void parse(String[] input) {
        List<Integer> list = new ArrayList<>();
        int currTimestamp = Integer.valueOf(input[1]);
        if (map.containsKey(input[0])) {
            list = map.get(input[0]);
            int timestamp = list.get(list.size() - 1);

            if (timestamp + 10 < currTimestamp) {
                list.add(currTimestamp);
            }
        } else {
            list.add(currTimestamp);
        }
        map.put(input[0], list);
    }

}
