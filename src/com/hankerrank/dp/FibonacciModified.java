package com.hankerrank.dp;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BigInteger t0 = in.nextBigInteger();
        BigInteger t1 = in.nextBigInteger();
        int n = in.nextInt();

        for (int i = 2; i < n; i++) {
           BigInteger tmp = t1;
           t1 = t0.pow(2).add(t1.pow(2));
           t0 = tmp;
        }

        System.out.println(t1.toString());
    }
}
