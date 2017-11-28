package com.leetcode.permutation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        int product = 1;
        for(int i=1;i<=n;i++) {
            nums.add(i);
            product *= i;
        }
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<n;i++) {
            product /= (n-i);
            int index = (k-1) / product; //decide the low boundary
            buffer.append(nums.remove(index));
            k -= index * product;
        }
        return buffer.toString();
    }
    
    @Test
    public void test0() {
        int n = 4, k =3;
        assertEquals(1324, getPermutation(n, k));
    }

}
