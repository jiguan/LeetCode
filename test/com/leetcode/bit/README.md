# Bit manipulation

## Mod operations

```java
(a + b) % p = (a % p + b % p) % p
(a * b) % p = (a % p * b % p) % p
 a ^ b % p = ((a % p) ^ b) % p
```

## Bit operations

Eliminate the last bit: `n & (n - 1)`

- [Hamming Distance](https://discuss.leetcode.com/topic/72317/java-solution)

Get the last non-zero bit: `n & -n`

- [Single Number III](https://leetcode.com/problems/single-number-iii/description/)

Get all 1: `~0`

## Calculate sqrt root

```java
while (r * r > x) {
    r = (r + x / r) / 2;
}
```

- For range query questions, think about calculate a array to store them all

  [XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarray/)

## Questions

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

 - 371 [Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/description/)
#### Medium
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
  
