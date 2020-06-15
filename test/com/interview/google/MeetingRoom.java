package com.interview.google;

import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;
import org.junit.Test;

/*
 * Google | Onsite | Meeting Rooms 3
 * 
 * Given a list of intervals calendar and a number of available conference rooms. For each query,
 * return true if the meeting can be added to the calendar successfully without causing a conflict,
 * otherwise false. A conference room can only hold one meeting at a time.
 * 
 * Example 1:
 * 
 * Input: calendar = [[1, 2], [4, 5], [8, 10]], rooms = 1, queries = [[2, 3], [3, 4]] Output: [true,
 * true] Example 2:
 * 
 * Input: calendar = [[1, 2], [4, 5], [8, 10]], rooms = 1, queries = [[4, 5], [5, 6]] Output:
 * [false, true] Example 3:
 * 
 * Input: calendar = [[1, 3], [4, 6], [6, 8], [9, 11], [6, 9], [1, 3], [4, 10]] rooms = 3 queries =
 * [[1, 9], [2, 6], [7, 9], [3, 5], [3, 9], [2, 4], [7, 10], [5, 9], [3, 10], [9, 10]] Output:
 * [false, true, false, true, false, true, false, false, false, true]
 * 
 */
public class MeetingRoom {
    public boolean[] check(int[][] calendar, int rooms, int[][] query) {
        Arrays.sort(calendar, (a, b) -> a[0] - b[0]);
        int n = calendar[calendar.length - 1][1];
        // how many rooms are occupied on day i
        int used[] = new int[n + 1];
        for (int i = 0; i < calendar.length; i++) {
            int start = calendar[i][0];
            int end = calendar[i][1];
            while (start < end) {
                used[start]++;
                start++;
            }
        }

        boolean[] result = new boolean[query.length];

        for (int i = 0; i < query.length; i++) {
            int start = query[i][0];
            int end = query[i][1];

            while (start < end) {
                if (used[start] >= rooms) {
                    result[i] = false;
                    break;
                }
                if (start == end - 1) {
                    result[i] = true;
                }
                start++;
            }
        }
        return result;
    }

    @Test
    public void test0() {
        int[][] calendar = {{1, 3}, {4, 6}, {6, 8}, {9, 11}, {6, 9}, {1, 3}, {4, 10}};
        int room = 3;
        int[][] query =
                {{1, 9}, {2, 6}, {7, 9}, {3, 5}, {3, 9}, {2, 4}, {7, 10}, {5, 9}, {3, 10}, {9, 10}};
        boolean[] expect = {false, true, false, true, false, true, false, false, false, true};
        assertArrayEquals(expect, check(calendar, room, query));
    }
}
