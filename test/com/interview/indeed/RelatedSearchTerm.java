package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/* @formatter:off
 * Related Search Terms. Building relationship of search terms based on user activity history
 * Input:
 * List<User ID, Search Term>
 * Output:
 * Related Search Terms
 * Examples:
 * Input:
 * A, Book
 * A, Tools
 * A, Music
 * B, Tools
 * B, Movie
 * C, Movie
 * Output:
 * None,  // A is the first user in the history, so there is no related terms.
 * None,  // A is the first user in the history, so there is no related terms.
 * None,  // A is the first user in the history, so there is no related terms.
 * Book, Music // According to A's data, we know <Book, Tools, Music> are related search terms
 * None // B is the first user to search :Movie"
 * Tools // According to B's data, we know <Tools, Movie> are related search terms.
 * @formatter:on
 */
public class RelatedSearchTerm {

    class Pair {
        int maxCount;
        List<String> words;

        public Pair() {
            maxCount = 0;
            words = new ArrayList<>();
        }
    }

    // User -> searched terms
    Map<String, Set<String>> users = new HashMap<>();
    // term -> related word and times
    Map<String, Map<String, Integer>> relates = new HashMap<>();
    // cache history
    Map<String, Pair> ans = new HashMap<>();

    // return the most related term
    public String search(String user, String word) {
        if (!users.containsKey(user)) {
            users.put(user, new HashSet<>());
        }
        if (!relates.containsKey(word)) {
            relates.put(word, new HashMap<>());
        }
        if (!ans.containsKey(word)) {
            ans.put(word, new Pair());
        }

        Pair max = ans.get(word);

        // update maps
        for (String term : users.get(user)) {
            // update word -> term
            if (!relates.get(word).containsKey(term)) {
                relates.get(word).put(term, 1);
            } else {
                relates.get(word).put(term, relates.get(word).get(term) + 1);
            }
            
            Map<String, Integer> wordRelated = relates.get(word);
            wordRelated.put(term, wordRelated.getOrDefault(term, 0) + 1);

            if (ans.get(word).maxCount == wordRelated.get(term)) {
                ans.get(word).words.add(term);
            } else if (ans.get(word).maxCount < wordRelated.get(term)) {
                ans.put(word, new Pair());
                ans.get(word).maxCount = wordRelated.get(term);
                ans.get(word).words.add(term);
            }

            // update term -> word
            Map<String, Integer> termRelated = relates.get(term);
            termRelated.put(word, termRelated.getOrDefault(word, 0) + 1);

            if (ans.get(term).maxCount == relates.get(term).get(word))
                ans.get(term).words.add(word);
            else if (ans.get(term).maxCount < relates.get(term).get(word)) {
                ans.put(term, new Pair());
                ans.get(term).maxCount = relates.get(term).get(word);
                ans.get(term).words.add(word);
            }
        }
        users.get(user).add(word); // do not forget this

        StringBuilder sb = new StringBuilder();
        sb.append(max.maxCount);
        for (String each : max.words) {
            sb.append(" " + each);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RelatedSearchTerm qr = new RelatedSearchTerm();
        Scanner sc = new Scanner(System.in);
        int numLines = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numLines; i++) {
            String[] parts = sc.nextLine().split(" ");
            System.out.println(qr.search(parts[0], parts[1]));
        }
        sc.close();
    }
}
