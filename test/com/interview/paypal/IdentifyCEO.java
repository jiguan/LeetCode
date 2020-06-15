package com.interview.paypal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * N employees in a party and one of them is CEO of the company. Identify who is the CEO? Given that
 * CEO is the ONLY person who satisfies these condition: a) Every other employee knows CEO. b) CEO
 * knows no one.
 * 
 * You are given an API, bool knows(int emp1, int emp2) which will return true if emp1 knows emp2.
 * 
 */
public class IdentifyCEO {

    boolean[][] matrix;

    private boolean knows(int emp1, int emp2) {
        return matrix[emp1][emp2];
    }

    public int identify() {
        int n = matrix.length;

        int a = 0;
        int b = n - 1;

        while (a < b) {
            if (knows(a, b)) {
                // a knows b -> a must not be the CEO
                a++;
            } else {
                // a doens't know b -> b must not be the CEO
                b--;
            }
        }

        // Check if a is actually 
        // a celebrity or not
        for (int i = 0; i < n; i++) {
            // If any person doesn't know 'a' or 'a' doesn't know any person, return -1
            if (i != a && (knows(a, i) || !knows(i, a))) {
                return -1;
            }
        }
        return a;
    }

    @Test
    public void test0() {
        // Person with 2 is CEO
        this.matrix = new boolean[][] {{false, false, true, false}, {false, false, true, false},
                {false, false, false, false}, {false, false, true, false}};
        assertEquals(2, identify());
    }

}
