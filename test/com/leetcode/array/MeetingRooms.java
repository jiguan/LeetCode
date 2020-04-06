package com.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import com.leetcode.util.Interval;

public class MeetingRooms {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        int end = intervals[0].end;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start < end) return false;
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] < end) {
                return false;
            }
            end = Math.max(end, intervals[i][1]);
        }
        return true;
    }
}
