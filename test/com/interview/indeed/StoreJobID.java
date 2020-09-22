package com.interview.indeed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreJobID {
    Map<Long, Boolean> record = new HashMap<>();

    public StoreJobID(List<Long> jobids) {
        for (Long id : jobids) {
            record.put(id, true);
        }
    }

    public void expire(long jobid) {
        if (record.containsKey(jobid)) {
            record.put(jobid, false);
        }
    }

    public boolean isexpired(long jobid) {
        return record.get(jobid);
    }

    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        int begin = intervals[0][0];
        int end = intervals[0][1];

        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; ++i) {
            if (end < intervals[i][0]) {
                res.add(new int[] {begin, end});
                begin = intervals[i][0];
            }
            end = Math.max(end, intervals[i][1]);

        }
        res.add(new int[] {begin, end});
        return res.toArray(new int[0][]);
    }

    // Follow up
    // long 8 bytes
    // 1 MB = 1024 byte
    // 1 GB = 1024 MB
    // 1 MB = 1024 KB
    // 16GB = 16 * 1024 * 1024 * 1024 = 17,179,869,184 byte
    // 17,179,869,184 / 8 = 2,147,483,648 IDs

    // bitset
    // 1 GB = 8,589,934,592 bits
    // 4 Billion bits = 0.5 GB

    // intervals, binary search
    
    
    
    
}
