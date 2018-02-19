package com.leetcode.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Arrays {
    public static boolean equals(int[] expected, int[] actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.length != actual.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) return false;
        }
        return true;
    }

    public static boolean equals(char[] expected, char[] actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.length != actual.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) return false;
        }
        return true;
    }

    public static boolean equals(char[][] expected, char[][] actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.length != actual.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (!equals(expected[i], actual[i])) return false;
        }
        return true;
    }

    public static boolean containSameElements(int[] expected, int[] actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.length != actual.length) return false;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < expected.length; i++) {
            set.add(expected[i]);
        }
        for (int i = 0; i < actual.length; ++i) {
            if (!set.remove(actual[i])) {
                return false;
            }
        }
        return true;
    }

    /*****************
     * @param coll
     *            a collection of Comparable objects
     * @param n
     *            the position of the desired object, using the ordering defined
     *            on the list elements
     * @return the nth smallest object
     *******************/
    public static <T> T nth(ArrayList<T> coll, int n, Comparator<T> comp) {
        T result, pivot;
        ArrayList<T> underPivot = new ArrayList<>(), overPivot = new ArrayList<>(), equalPivot = new ArrayList<>();

        // choosing a pivot is a whole topic in itself.
        // this implementation uses the simple strategy of grabbing something
        // from the middle of the ArrayList.

        pivot = coll.get(n / 2);

        // split coll into 3 lists based on comparison with the pivot

        for (T obj : coll) {
            int order = comp.compare(obj, pivot);
            if (order < 0) // obj < pivot
                underPivot.add(obj);
            else if (order > 0) // obj > pivot
                overPivot.add(obj);
            else // obj = pivot
                equalPivot.add(obj);
        } // for each obj in coll

        // recurse on the appropriate list

        if (n < underPivot.size()) result = nth(underPivot, n, comp);
        // fall into the set equalPivot
        else if (n < underPivot.size() + equalPivot.size()) // equal to pivot;
                                                            // just return it
            result = pivot;
        else // everything in underPivot and equalPivot is too small. Adjust n
             // accordingly in the recursion.
            result = nth(overPivot, n - underPivot.size() - equalPivot.size(), comp);

        return result;
    } // nth(coll, n)

}
