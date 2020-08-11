# String

## Regex

- Split by spaces: ```split("\\s+")```

## Reverse strings

If we need to reverse the sentence's order but keep each word's order, try to

1. reverse each word
2. reverse the whole sentence

    ```txt
    sky is blue
    yks si eulb
    blue is sky
    ```

## Counting letters

If the question is comparison for chars, using a count array with positive/negative value to indicate it shows up or not

- [Reverse Words in a String II](https://leetcode.com/problems/reverse-words-in-a-string-ii/)
- [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
- [Guess the Word](https://leetcode.com/problems/guess-the-word/) - hard, counting letters to find out the word with "max coverage"

If we want to check if one word is a subset characters of another one, rather than outputting all combinations into a HashMap, we could count the number of letters.

```java
private boolean isValid(int[] wordA, int[] wordB) {
    for (int i = 0; i < 26; ++i) {
        // If a letter exists in existing wordA but not in wordB
        // There is no way wordA is a subset of wordB
        if (wordA[i] < wordB[i]) {
            return false;
        }
    }
    return true;
}
```

- Blue Nile's assignment: scrabble-solver-service

However, if the order matters we cannot use this approach.

- [Expressive Words](https://leetcode.com/problems/expressive-words/)

## Add a dummy character

Some questions require one extra execution at the end of the iteration. We could add an additional unharmful element.

- [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/description/)

## Time calculation

- [Next Closest Time](NextClosestTime.java) - Interesting way to calculate it.

## KMP

Knuth Morris Pratt algorithm [Code](../../../com/algorithm/KnuthMorrisPratt.java)

1. First build a longest suffix-prefix table (**lsp**) which recording the next position of the matched character with current one (previously matched character's index + 1).
1. Then initialize two pointers, **s** for the searched and **t** for the pattern. Compare their corresponding characters and if they are matched, both move forward. Otherwise, using the **lsp** table, **t** keeps setting to the previous one (**t** - 1, still matched character)'s next position until it becomes 0 or its character matches current one.

- [Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern/description/)

    ```java
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        // longest suffix-prefix
        int[] lsp = new int[len];

        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            // previous character's start position
            int j = lsp[i - 1];
            while (j != 0 && c != s.charAt(j)) {
                j = lsp[j - 1];
            }

            if (c == s.charAt(j)) {
                lsp[i] = j + 1;
            }
        }
        // If there is repeated substring pattern, the lsp would be like [0, 0, 0, 1, 2, 3, 4, 5, 6]
        // len - lsp[last] is the pattern length
        return lsp[len - 1] != 0 && len % (len - lsp[len - 1]) == 0;
    }
    ```

## Palindrome

- [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/) - Hash Set, using given letters to reform a new palindrome string

### Manacher’s Algorithm

Linear Time Longest Palindromic Substring

## Questions

- [Longest Word in Dictionary through Deleting](https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/). Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

__Dictionary word matching__ question: The idea is using an index and skip some characters if necessary.

- [Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/) Hard, we could use greedy algorithm approach.

### Repeat characters

- [Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern/)


### Remove characters
- [Make The String Great](https://leetcode.com/problems/make-the-string-great/) - Easy. But check my code, how to use another array to store result string is interesting.