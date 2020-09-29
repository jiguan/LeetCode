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

    // related words occurs maxCount times
    class Record {
        int maxCount;
        List<String> words;

        public Record() {
            maxCount = 0;
            words = new ArrayList<>();
        }
    }

    // User -> searched terms
    Map<String, Set<String>> users = new HashMap<>();
    // term -> related word and times
    Map<String, Map<String, Integer>> relates = new HashMap<>();
    // store previous, word - related time, max occurrence
    Map<String, Record> history = new HashMap<>();

    // return the most related term
    public String search(String user, String word) {
        if (!users.containsKey(user)) {
            users.put(user, new HashSet<>());
        }
        if (!relates.containsKey(word)) {
            relates.put(word, new HashMap<>());
        }
        if (!history.containsKey(word)) {
            history.put(word, new Record());
        }

        // Get all words this user searched
        for (String term : users.get(user)) {
            // update word -> term
            // related term - times
            Map<String, Integer> wordRelated = relates.get(word);
            wordRelated.put(term, wordRelated.getOrDefault(term, 0) + 1);

            // any same occurrence word
            if (history.get(word).maxCount == wordRelated.get(term)) {
                history.get(word).words.add(term);
            } else if (history.get(word).maxCount < wordRelated.get(term)) {
                // a new most frequent word occurs
                history.put(word, new Record());
                history.get(word).maxCount = wordRelated.get(term);
                history.get(word).words.add(term);
            }

            // update term -> word
            Map<String, Integer> termRelated = relates.get(term);
            termRelated.put(word, termRelated.getOrDefault(word, 0) + 1);

            if (history.get(term).maxCount == relates.get(term).get(word))
                history.get(term).words.add(word);
            else if (history.get(term).maxCount < relates.get(term).get(word)) {
                history.put(term, new Record());
                history.get(term).maxCount = relates.get(term).get(word);
                history.get(term).words.add(word);
            }
        }
        users.get(user).add(word); // do not forget this

        StringBuilder sb = new StringBuilder();
        sb.append(history.get(word).maxCount);
        for (String each : history.get(word).words) {
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
