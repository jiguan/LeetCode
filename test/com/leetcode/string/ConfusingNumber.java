package com.leetcode.string;

/*
 * Confusing Number
 * 
 * Given a number N, return true if and only if it is a confusing number, which satisfies the
 * following condition:
 * 
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180
 * degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees,
 * they become invalid. A confusing number is a number that when rotated 180 degrees becomes a
 * different number with each digit valid.
 */
public class ConfusingNumber {
    public boolean confusingNumber(int N) {
        String s = String.valueOf(N);
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '2' || c == '3' || c == '4' || c == '5' || c == '7')
                return false;
            if (c == '6')
                sb.append('9');
            else if (c == '9')
                sb.append('6');
            else
                sb.append(c);
        }
        return !sb.reverse().toString().equals(s);
    }
}
