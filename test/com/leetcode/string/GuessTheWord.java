package com.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.leetcode.util.Master;

public class GuessTheWord {
    // We are given a word list of unique words, each word is 6 letters long, and one word in this
    // list is chosen as secret.
    // If you have made 10 or less calls to master.guess and at least one of these guesses was the
    // secret, you pass the testcase.
    public void findSecretWord(String[] wordlist, Master master) {
        int wordLen = 6;
        for (int t = 0; t < 10; ++t) {
            int count[][] = new int[wordLen][26];
            // count total letters
            for (String w : wordlist) {
                for (int i = 0; i < wordLen; ++i) {
                    count[i][w.charAt(i) - 'a']++;
                }
            }
            String guess = wordlist[0];
            int best = 0;
            for (String w : wordlist) {
                int score = 0;
                for (int i = 0; i < wordLen; ++i) {
                    // at i, how many times the letter w.charAt(i) occurs
                    score += count[i][w.charAt(i) - 'a'];
                }
                // try to find a word with most common letters
                if (score > best) {
                    guess = w;
                    best = score;
                }
            }
            int diff = master.guess(guess);
            List<String> tmp = new ArrayList<String>();
            for (String w : wordlist) {
                if (match(guess, w) == diff) {
                    tmp.add(w);
                }
            }
            wordlist = tmp.toArray(new String[0]);
        }
    }

    public void findSecretWordSameWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            Map<String, Integer> counts = new HashMap<>();
            for (String w1 : wordlist) {
                for (String w2 : wordlist) {
                    if (match(w1, w2) == 0) {
                        counts.put(w1, counts.getOrDefault(w1, 0) + 1);
                    }
                }
            }

            String guess = "";
            int count = wordlist.length;
            for (String w : wordlist) {
                if (counts.getOrDefault(w, 0) < count) {
                    guess = w;
                    count = counts.getOrDefault(w, 0);
                }
            }
            x = master.guess(guess);
            List<String> tmp = new ArrayList<String>();
            for (String w : wordlist) {
                if (match(guess, w) == x) {
                    tmp.add(w);
                }
            }
            wordlist = tmp.toArray(new String[0]);
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) == b.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }
}



