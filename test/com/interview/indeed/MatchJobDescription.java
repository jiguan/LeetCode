package com.interview.indeed;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class MatchJobDescription {
    public String getHighestTitle(String rawTitle, String[] cleanTitles) {
        String res = "";
        int highScore = 0;
        for (String title : cleanTitles) {
            int score = getScore(rawTitle, title);
            if (score > highScore) {
                highScore = score;
                res = title;
            }
        }
        return res;
    }

    private int getScore(String rawTitle, String cleanTitle) {
        int res = 0;
        Set<String> cleanSet = new HashSet<>();
        for(String clean : cleanTitle.split(" ")) {
            cleanSet.add(clean);
        }
        
        String[] raws = rawTitle.split(" ");
        for(String raw : raws) {
            if(cleanSet.contains(raw)) {
                res++;
            }
        }
        return res;
    }

    public String getHightestTitleWithDup(String rawTitle, String[] cleanTitles) {
        String res = "";
        int highScore = 0;
        String[] rA = rawTitle.split(" ");
        for (String ct : cleanTitles) {
            String[] cA = ct.split(" ");
            int score = getScoreWithDup(rA, cA);
            System.out.println("temp is " + score);
            if (score > highScore) {
                highScore = score;
                res = ct;
            }
        }
        return res;
    }

    public int getScoreWithDup(String[] raw, String[] clean) {
         int m = raw.length, n = clean.length;
        int cache[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    cache[i][j] = 0;
                } else if (raw[i - 1].equals(clean[j - 1])) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }
        return cache[m][n];
    }
    
    
    @Test
    public void test0() {
        String rawTitle = "senior software engineer";
        String[] cleanTitles =
                {"mechanical engineer"};

        String result = getHighestTitle(rawTitle, cleanTitles);
        assertEquals("software engineer", result);
    }
    
    @Test
    public void test1() {
        String rawTitle = "senior software software engineer";
        String[] cleanTitles =
                {"software software engineer", "senior mechanical engineer"};

        String result = getHightestTitleWithDup(rawTitle, cleanTitles);
        assertEquals("software software engineer", result);
    }
}
