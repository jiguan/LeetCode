package com.interview.pinintest;

/*
 * The knows API is defined in the parent class Relation. boolean knows(int a, int b);
 */

public class FindTheCelebrity extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }
        return candidate;
    }
}


class Relation {
    protected boolean knows(int candidate, int i) {
        // TODO Auto-generated method stub
        return false;
    }
}
