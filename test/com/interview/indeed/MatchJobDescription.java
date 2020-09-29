package com.interview.indeed;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;

public class MatchJobDescription {
    static List<Set<String>> jds = new ArrayList<>();
    static Map<String, Integer> match = new HashMap<>();

    public static void storeDocument(final String document, final int documentNumber) {
        String[] split = document.split(" ");
        Set<String> jd_set = new HashSet<>(Arrays.asList(split));
        jds.add(jd_set);
    }

    private static int getMatch(String search, int jobNum) {
        int res = 0;
        for (String word : search.split(" ")) {
            Set<String> jd = jds.get(jobNum);
            if (jd.contains(word)) {
                res++;
            }
        }
        return res;
    }

    public static String performSearch(final String search) {
        // Todo: change the order so that we could dump k+1 element
        PriorityQueue<Result> pq =
                new PriorityQueue<MatchJobDescription.Result>(new Comparator<MatchJobDescription.Result>() {
                    @Override
                    public int compare(Result o1, Result o2) {
                        if (o1.match > o2.match) {
                            return -1;
                        } else if (o1.match < o2.match) {
                            return 1;
                        } else {
                            return Integer.compare(o1.jobNum, o2.jobNum);
                        }
                    }
                });

        for (int i = 0; i < jds.size(); ++i) {
            int match = getMatch(search, i);
            if (match != 0) {
                pq.add(new Result(match, i));
            }
        }

        if (pq.isEmpty()) return "-1";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10 && !pq.isEmpty(); ++i) {
            if (i != 0) {
                sb.append(" " + pq.poll().jobNum);
            } else {
                sb.append(pq.poll().jobNum);
            }
        }
        return sb.toString();
    }

    static class Result {
        int match;
        int jobNum;

        Result(int match, int jobNum) {
            this.match = match;
            this.jobNum = jobNum;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "match:" + match + ", jobNum:" + jobNum;
        }

    }


    @Test
    public void test0() {
        storeDocument("experienced software developer python", 0);
        storeDocument("experienced developer javascript css html react", 1);
        storeDocument("technical recuriter junior software", 2);

        assertEquals("0 2", performSearch("software"));
    }

    @Test
    public void test1() {

        jds = new ArrayList<>();
        // static Map<Integer, Map<String, Integer>> match_cache = new HashMap<>();
        // each word, how many matches
        // word - job - match
        match = new HashMap<>();

        storeDocument("match1 match2", 0);
        storeDocument("match2 match1 match3", 1);
        storeDocument("match1 match2", 2);

        assertEquals("1 0 2", performSearch("match3 match2 match1"));
    }


    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        // Read documents
        for (int i = 0; i < N; i++) {
            storeDocument(br.readLine(), i);
        }

        final int M = Integer.parseInt(br.readLine());
        // Read searches
        for (int j = 0; j < M; j++) {
            System.out.println(performSearch(br.readLine()));
        }
    }
}
