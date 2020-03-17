package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import java.util.Scanner;
import org.junit.Test;

public class FractionAdditionAndSubtraction {
    public String fractionAddition(String expression) {
        Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
        int A = 0, B = 1;
        while (sc.hasNext()) {
            int a = sc.nextInt(), b = sc.nextInt();
            A = A * b + a * B;
            B *= b;
            int g = gcd(A, B);
            A /= g;
            B /= g;
        }
        return A + "/" + B;
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : Math.abs(b);
    }

    @Test
    public void test0() {
        String expression = "-1/2+1/2";
        String expected = "0/1";
        assertEquals(expected, fractionAddition(expression));
    }
}
