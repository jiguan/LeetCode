package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0, radis = 0;
        for (int house : houses) {
            // average is slightly over house, i is more closer since it is
            // ascending
            while (i < heaters.length - 1 && heaters[i + 1] - house < house - heaters[i]) {
                i++;
            }

            radis = Math.max(radis, Math.abs(heaters[i] - house));
        }

        return radis;
    }

    @Test
    public void test0() {
        int[] houses = new int[]{1, 2, 3, 4};
        int[] heaters = new int[]{1, 4};

        assertEquals(Integer.valueOf(1), Integer.valueOf(findRadius(houses, heaters)));
    }

    @Test
    public void test1() {
        int[] houses = new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923};
        int[] heaters = new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612};

        assertEquals(Integer.valueOf(161834419), Integer.valueOf(findRadius(houses, heaters)));
    }
}
