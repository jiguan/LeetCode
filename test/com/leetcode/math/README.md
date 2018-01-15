## Math questions
### Keynote
- Greatest common divisor
	```
	private int gcd(int a, int b) {
		while (b != 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
	```
	Question: 365. [Water and Jug Problem](WaterAndJugProblem.java)
- How to get sqrt root
   1. Newton's method:
   ```
   long r = x;
   while (r*r > x) {
      r = (r + x/r) / 2;
   }    
   return (int) r;
   ```
   2. Binary  search
   ```
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
   ```
   public boolean isPowerOfThree(int n) {
      return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
   } 
   ``` 
   - Iterative
   ```
   public boolean isPowerOfThree(int n) {
      if(n > 1)
         while(n % 3 == 0) n /= 3;
      return n==1;
   }
   ```
   - Log
   ```
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

### Questions
- [Convert a Number to Hexadecimal](ConvertANumberToHexadecimal.java)
    How to do division and consider all edge cases.


#### Medium 
  - 118 Pascal's Triangle
  - 367 Valid Perfect Square [question](https://leetcode.com/problems/valid-perfect-square)
  
  
#### Counting questions
  -  172 [Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)