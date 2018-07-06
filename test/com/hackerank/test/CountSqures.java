package com.hackerank.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountSqures {
    public int countSquare(boolean[][] ver, boolean[][] hor) {
        if (ver == null || hor == null || ver.length == 0 || ver[0].length == 0 || hor.length == 0
                || hor[0].length == 0) {
            return 0;
        }
        // till now, how many consecutive matches
        int[][] dpV = new int[ver.length][ver[0].length];
        int[][] dpH = new int[hor.length][hor[0].length];
        int res = 0;
        for (int i = 0; i < dpV.length; ++i) {
            for (int j = 0; j < dpV[0].length; ++j) {
                if (ver[i][j]) {
                    dpV[i][j] = i == 0 ? 0 : (dpV[i - 1][j] + 1);
                } else {
                    dpV[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < dpH.length; ++i) {
            for (int j = 0; j < dpH[0].length; ++j) {
                if (hor[i][j]) {
                    dpH[i][j] = j == 0 ? 0 : (dpH[i][j - 1] + 1);
                } else {
                    dpH[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < dpH.length; ++i) {
            for (int j = 0; j < dpH[0].length; ++j) {
                // i + l <= vet[0].length; j + l <= hor[0].length
                for (int l = 1; l < Math.min(hor[0].length - j, ver[0].length - i); ++l) {
                    if (dpH[i][l + j] >= l && dpH[i + l][l + j] >= l && dpV[j + l][i] >= l && dpV[j + l][l + i] >= l) {
                        ++res;
                    }
                    // no needs to increase l
                    if (dpH[i][l + j] < l) {
                        break;
                    }
                }
            }
        }
        return res;
    }

    //@formatter:off
    //  --- ---
    // |   |   |
    //  --- ---
    // |   |   |
    //  --- ---
    //@formatter:on
    @Test
    public void test0() {
        boolean[][] ver = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};
        boolean[][] hor = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};
        assertEquals(5, countSquare(ver, hor));
    }

    //@formatter:off
    //  --- ---
    // |   |   |
    //  --- ---
    // |   |
    //  ---
    //@formatter:on
    @Test
    public void test1() {
        boolean[][] ver = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, false}};
        boolean[][] hor = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, false}};
        assertEquals(3, countSquare(ver, hor));
    }

    //@formatter:off
    //  ---
    // |   |
    //  --- 
    //@formatter:on
    @Test
    public void test2() {
        boolean[][] ver = new boolean[][]{{true, true}, {true, true}};
        boolean[][] hor = new boolean[][]{{true, true}, {true, true}};
        assertEquals(1, countSquare(ver, hor));
    }

    //@formatter:off
    //  --- --- 
    // |   |   | 
    //  --- ---
    //@formatter:on
    @Test
    public void test3() {
        boolean[][] ver = new boolean[][]{{true, true}, {true, true}, {true, true}};
        boolean[][] hor = new boolean[][]{{true, true, true}, {true, true, true}};
        assertEquals(2, countSquare(ver, hor));
    }

    //@formatter:off
    //  --- --- ---
    // |   |   |   |
    //  --- --- ---
    //@formatter:on
    @Test
    public void test4() {
        boolean[][] ver = new boolean[][]{{true, true}, {true, true}, {true, true}, {true, true}};
        boolean[][] hor = new boolean[][]{{true, true, true, true}, {true, true, true, true}};
        assertEquals(3, countSquare(ver, hor));
    }

    //@formatter:off
    //  --- 
    //@formatter:on
    @Test
    public void test5() {
        boolean[][] ver = new boolean[][]{{true}, {true}};
        boolean[][] hor = new boolean[][]{{true, true}};
        assertEquals(0, countSquare(ver, hor));
    }
}
