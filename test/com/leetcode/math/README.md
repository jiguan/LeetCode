# Math

## Greatest Common Divisor (GCD)

```java
private int gcd(int a, int b) {
     // a > b, otherwise they will swap
     while (b != 0) { // loop until b = 0
          int tmp = b;
          b = a % b;
          a = tmp;
     }
     // a % b = 0, a is the GCD
     return a;
}
```

As a helper function

```java
private int gcd(int a, int b) {
   return a != 0 ? gcd(b % a, a) : Math.abs(b);
}
```

- [Fraction Addition and Subtraction](https://leetcode.com/problems/fraction-addition-and-subtraction/)
- [Water and Jug Problem](https://leetcode.com/problems/water-and-jug-problem/)

## Calculate sqrt root

Newton's method:

```java
long r = x;
while (r * r > x) {
   r = (r + x / r) / 2;
}
return (int) r;
```

Binary search

```java
if (x <= 1) return x;
int left = 1, right = x;
while (left < right) {
   int mid = (right - left) / 2 + left;
   // mid * mid > x
   if (mid > x / mid) {
      right = mid;
   } else {
      left = mid + 1;
   }
}
return left - 1;
```

## Power of Three

Recursive

```java
public boolean isPowerOfThree(int n) {
   return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
}
```

Iterative

```java
public boolean isPowerOfThree(int n) {
   if(n > 1)
      while(n % 3 == 0) n /= 3;
   return n==1;
}
```

Log

```java
public boolean isPowerOfThree(int n) {
   if(n <= 0) return false; 
   double r = Math.log10(n) / Math.log10(3);
   if(r % 1.0 == 0) {
      return true;
   } else {
      return false;
   }
}
```

## Count Primes

```java
// O(n * log(log(n)))
public int countPrimes(int n) {
   if (n < 3) return 0;
   // for n = 2
   int count = 1;
   boolean[] arr = new boolean[n];
   // i = 3, i*j = 6, 9, 12, .., n/3
   // i = 5, i*j = 10, 15, 20, .., n/5
   // ...
   // n/3 + n/5 + n/7, (n/9 is skipped), n/11 => O(n * log(log(n)))
   for (int i = 3; i < n; i += 2) {
      if (arr[i] == false) {
         count++;
         for (int j = 2; i * j < n; j++) {
            arr[i * j] = true;
         }
      }
   }
   return count;
}
```

[Proof of sum(1/p)=ln(ln(n)) + O(1), where p is prime](http://www.cs.umd.edu/~gasarch/BLOGPAPERS/sump.pdf)

## Median

### Median minimus the sum of distance to all points

```text
val = sum(xi - m)
```

The val is minimized when the `m` is the median

- [Best Meeting Point](https://leetcode.com/problems/best-meeting-point/)
- [Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/) - No clue at first sight, interesting question
- [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) - Hard

## Sum of array

```txt
   N = k, k + 1, k + 2, ..., k + (i - 1)
   N = (k + k + i - 1) * i / 2
   N = ki + (i - 1) * i / 2
   ki = N - (i - 1) * i / 2
```

If there is such a `i`, then we know one consecutive array exists.

- [Consecutive Numbers Sum](https://leetcode.com/problems/consecutive-numbers-sum/)





## Questions

- [Total Hamming Distance](https://leetcode.com/problems/total-hamming-distance/description/) - number of 1-0 combination = pick one from 1 * pick one from 0 = num(1) * num(0)
- [Convert a Number to Hexadecimal](ConvertANumberToHexadecimal.java)
    How to do division and consider all edge cases.
- [Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/) Use a map to record previous remaining and its index
- 118 Pascal's Triangle
- [Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square)

### Counting questions

- 172 [Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)
