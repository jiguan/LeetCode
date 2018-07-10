package com.leetcode.bit;

/**
 * Given a positive integer n and you can do operations as follow:
 * 
 * If n is even, replace n with n/2. If n is odd, you can replace n with either n + 1 or n - 1. What is the minimum
 * number of replacements needed for n to become 1?
 */
public class IntegerReplacement {
    public int integerReplacement0(int n) {
        if (n == Integer.MAX_VALUE) return 32; // n = 2^31 - 1
        int res = 0;
        while (n > 1) {
            if (n % 2 == 0) n /= 2;
            else {
                if ((n + 1) % 4 == 0 && n != 3) ++n;
                else
                    --n;
            }
            ++res;
        }
        return res;
    }

    // we want to erase as many 1 as possible
    public int integerReplacement(int n) {
        // if not check (n == Integer.MAX_VALUE), ++n result overflow -> -2147483648 
        int res = 0;
        while (n != 1) {
            // last bit is 0, even number
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n != 3 && (n & 3) == 3) {
                ++n;
            } else {
                --n;
            }
            ++res;
        }
        return res;
    }
}
