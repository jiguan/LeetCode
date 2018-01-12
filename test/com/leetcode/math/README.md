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


#### Medium 
  - 9 Palindrome Number [question](https://leetcode.com/problems/palindrome-number)
  - 118 Pascal's Triangle
  - 367 Valid Perfect Square [question](https://leetcode.com/problems/valid-perfect-square)
  
  
#### Counting questions
  -  172 [Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)