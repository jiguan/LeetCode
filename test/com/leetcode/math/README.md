# Math

## Keynote

- Greatest Common Divisor (GCD)

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

Question: 365. [Water and Jug Problem](WaterAndJugProblem.java)

- How to get sqrt root

  - Newton's method:

   ```java
   long r = x;
   while (r*r > x) {
      r = (r + x/r) / 2;
   }
   return (int) r;
   ```

  - Binary search

   ```java
   if (x <= 1) return x;
   int left = 1, right = x;
   while (left < right) {
      int mid = (right - left >> 1) + left;
      // mid * mid > x
      if (mid > x / mid) {
         right = mid;
      } else {
         left = mid + 1;
      }
   }
   return left - 1;
   ```

- Power of Three
  - Recursive

   ```java
   public boolean isPowerOfThree(int n) {
      return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
   }
   ```

  - Iterative

   ```java
   public boolean isPowerOfThree(int n) {
      if(n > 1)
         while(n % 3 == 0) n /= 3;
      return n==1;
   }
   ```

  - Log

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

  - Amount of combinations

   ```java
      int num = same * diff
   ```

   [Total Hamming Distance](https://leetcode.com/problems/total-hamming-distance/description/)

## Questions

- [Convert a Number to Hexadecimal](ConvertANumberToHexadecimal.java)
    How to do division and consider all edge cases.

- [Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/) Use a map to record previous remaining and its index


- 118 Pascal's Triangle
- [Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square)

### Counting questions

- 172 [Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)
