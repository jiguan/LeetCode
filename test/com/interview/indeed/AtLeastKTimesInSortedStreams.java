package com.interview.indeed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

// Given n sorted iterator, and a K, find nums which occurs at least k times over all iterators.
// Skip duplicated elements in same iterator
public class AtLeastKTimesInSortedStreams {

    public List<Integer> getNumsFromKStreams(List<Stream> streams, int k) {
        List<Integer> res = new ArrayList<>();
        if (streams == null || streams.size() < k)
            return res;

        PriorityQueue<Data> pq = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.val - o2.val;
            }
        });

        for (Stream s : streams) {
            if (s.move())
                pq.add(new Data(s));
        }

        // available streams less than k
        if (pq.size() < k)
            return res;

        int prevVal = -1, count = 0;
        while (!pq.isEmpty()) {
            Data data = pq.remove();
            int currVal = data.val;
            if (count == 0 || currVal != prevVal) {
                prevVal = currVal;
                count = 1;
            } else {
                count++;
            }

            while (data.stream.move()) {
                // get next
                int nextVal = data.stream.getValue();
                if (nextVal != currVal) {
                    data.val = nextVal;
                    pq.add(data);
                    break;
                }
            }

            if (count >= k) {
                res.add(currVal);
            }

            // exit in advance
            if (pq.size() < k)
                break;
        }
        return res;

    }

    // Follow up: What if one stream is much longer than others
    // Terminate when min heap size is smaller than k
}


class Data {
    int val;
    Stream stream;

    public Data(Stream s) {
        this.stream = s;
        this.val = s.getValue();
    }
}


class Stream {

    Iterator<Integer> iter;

    public boolean move() {
        return iter.hasNext();
    }

    public int getValue() {
        return iter.next();
    }
}
