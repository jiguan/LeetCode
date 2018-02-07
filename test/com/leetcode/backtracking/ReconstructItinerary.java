package com.leetcode.backtracking;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) {
                map.put(ticket[0], new ArrayList<>());
            }
            addToList(ticket[1], map.get(ticket[0]));
        }
        
        res.add("JFK");
        helper(map, "JFK", res, tickets.length);
        
        return res;
    }

    private boolean helper(Map<String, List<String>> map, String curCity, List<String> res, int ticketNum) {
        // All tickets have been used
        if (ticketNum <= 0) return true;

        if (!map.containsKey(curCity) || map.get(curCity).isEmpty()) {
            return false;
        }

        List<String> accessible = map.get(curCity);

        for (int i = 0; i < accessible.size(); ++i) {
            String city = accessible.remove(i);
            res.add(city);
            if (helper(map, city, res, ticketNum - 1)) {
                return true;
            }
            res.remove(res.size() - 1);
            addToList(city, accessible);
        }

        return false;
    }

    private void addToList(String val, List<String> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (val.compareTo(list.get(i)) < 0) {
                list.add(i, val);
                return;
            }
        }
        list.add(val);
    }

    @Test
    public void test0() {
        String[][] tickets = new String[][]{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<String> expected = Arrays.asList("JFK","ATL","JFK","SFO","ATL","SFO");
        assertEquals(expected, findItinerary(tickets));
    }
}
