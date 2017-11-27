## Bit manipulation

### Keynote
- n & (n - 1) will eliminate the last bit [Hamming Distance](https://discuss.leetcode.com/topic/72317/java-solution)


### Questions
#### Easy 
- 318 [Maximum Product of Word Lengths](https://leetcode.com/problems/maximum-product-of-word-lengths) [Solution](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/bit/MaximumProductofWordLengths.java)
```  
  Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
  You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
  
  Example:
  Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
  Return 16
  The two words can be "abcw", "xtfn"
```
### Medium
 - 477 [Total Hamming Distance](https://leetcode.com/problems/total-hamming-distance/discuss/)
```
Example:
Input: 4, 14, 2
Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
```
#### Hard
  
