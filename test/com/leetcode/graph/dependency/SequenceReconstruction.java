package com.leetcode.graph.dependency;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/*
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 ¡Ü n ¡Ü 104. Reconstruction
 * means building a shortest common supersequence of the sequences in seqs (i.e., a shortest
 * sequence so that all sequences in seqs are subsequences of it). Determine whether there is only
 * one sequence that can be reconstructed from seqs and it is the org sequence.
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // since the value is from 1 to n
        int n = org.length + 1;
        // val - index
        int[] positions = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < org.length; i++) {
            positions[org[i]] = i;
        }

        int count = org.length;
        for (List<Integer> seq : seqs) {
            int prevPosition = -1;
            for (int curr : seq) {
                if (curr >= positions.length || positions[curr] <= prevPosition) {
                    return false;
                }

                if (visited[curr] == false && (positions[curr] == prevPosition + 1)) {
                    count--;
                    visited[curr] = true;
                }
                prevPosition = positions[curr];
            }
        }

        return count == 0;
    }

    @Test
    public void test0() {
        int[] org = {1, 2, 3};
        List<List<Integer>> seqs = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3));
        // could be 1,2,3 or 1,3,2
        assertFalse(sequenceReconstruction(org, seqs));
    }

    @Test
    public void test1() {
        int[] org = {1, 2, 3};
        List<List<Integer>> seqs =
                Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3));
        assertTrue(sequenceReconstruction(org, seqs));
    }

    @Test
    public void test2() {
        int[] org = {4, 1, 5, 2, 6, 3};
        List<List<Integer>> seqs =
                Arrays.asList(Arrays.asList(5, 2, 6, 3), Arrays.asList(4, 1, 5, 2));
        assertTrue(sequenceReconstruction(org, seqs));
    }

    @Test
    public void test3() {
        int[] org = {1};
        List<List<Integer>> seqs = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        assertFalse(sequenceReconstruction(org, seqs));
    }
}
