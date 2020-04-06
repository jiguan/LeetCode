# String

## Regex

- Split by spaces: ```split("\\s+")```

## Keynote

If we need to reverse the sentence's order but keep each word's order, try to

  1. reverse each word
  2. reverse the whole sentence

```txt
sky is blue
yks si eulb
blue is sky
```

- [Reverse Words in a String II](https://leetcode.com/problems/reverse-words-in-a-string-ii/)

If the question is comparison for chars, using a count array with positive/negative value to indicate it shows up or not

- [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)

 If we want to check if one word is a child of another one, rather than using `Permutation`, we could count the number of letters and compare them

```java
private boolean isValid(int[] newWord, int[] existing) {
    for (int i = 0; i < existing.length; ++i) {
        // Some letters existing valid word but not in this newly pass in word
        // There is no way this ScrabbleWord could be a subset of this new word
        if (newWord[i] < existing[i]) {
            return false;
        }
    }
    return true;
}
```

- Blue Nile's assignment: scrabble-solver-service

__One more time extra__: Some questions require one extra execution at the end of the iteration. We could add an additional unharmful element.

- [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/description/)

Knuth Morris Pratt algorithm [Code](../../../com/algorithm/KnuthMorrisPratt.java)

1. First build a longest suffix-prefix table (**lsp**) which recording the next position of the matched character with current one (previously matched character's index + 1).
1. Then initialize two pointers, **s** for the searched and **t** for the pattern. Compare their corresponding characters and if they are matched, both move forward. Otherwise, using the **lsp** table, **t** keeps setting to the previous one (**t** - 1, still matched character)'s next position until it becomes 0 or its character matches current one.

   [Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern/description/)

## Questions

- [Longest Word in Dictionary through Deleting](https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/). Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

__Dictionary word matching__ question: The idea is using an index and skip some characters if necessary.

- [Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/) Hard, we could use greedy algorithm approach.

