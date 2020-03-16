package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // email - persons -- in index
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); ++j) {
                String email = account.get(j);
                if (!map.containsKey(email)) {
                    map.put(email, new ArrayList<Integer>());
                }
                // add this person's index
                map.get(email).add(i);
            }
        }

        boolean[] visited = new boolean[accounts.size()];
        List<List<String>> res = new LinkedList<>();
        for (int i = 0; i < accounts.size(); ++i) {
            Set<String> set = new TreeSet<>();
            dfs(accounts, i, map, visited, set);
            if (!set.isEmpty()) {
                List<String> list = new LinkedList<>(set);
                list.add(0, accounts.get(i).get(0));
                res.add(list);
            }
        }
        return res;
    }

    private void dfs(List<List<String>> accounts, int cur, Map<String, List<Integer>> map, boolean[] visited, Set<String> set) {
        if (visited[cur]) return;
        visited[cur] = true;
        for (int i = 1; i < accounts.get(cur).size(); ++i) {
            String email = accounts.get(cur).get(i);
            set.add(email);
            // search deeper to find emails belong to same person
            for (int index : map.get(email)) {
                dfs(accounts, index, map, visited, set);
            }
        }
    }

    @Test
    public void test0() {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));

        List<List<String>> actual = accountsMerge(accounts);
        assertTrue(3 == actual.size());
        // List<String> res1 = Arrays.asList("John", "john00@mail.com",
        // "john_newyork@mail.com", "johnsmith@mail.com");
        // List<String> res2 = Arrays.asList("John", "johnnybravo@mail.com");
        // List<String> res3 = Arrays.asList("Mary", "mary@mail.com");
    }

    @Test
    public void test1() {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David", "David0@m.co", "David1@m.co"));
        accounts.add(Arrays.asList("David", "David3@m.co", "David4@m.co"));
        accounts.add(Arrays.asList("David", "David4@m.co", "David5@m.co"));
        accounts.add(Arrays.asList("David", "David2@m.co", "David3@m.co"));
        accounts.add(Arrays.asList("David", "David1@m.co", "David2@m.co"));

        List<List<String>> actual = accountsMerge(accounts);
        assertTrue(1 == actual.size());
        // [["David","David0@m.co","David1@m.co","David2@m.co","David3@m.co","David4@m.co","David5@m.co"]]
    }
}
