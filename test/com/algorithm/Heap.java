package com.algorithm;

import java.util.ArrayList;
import java.util.List;

// https://www.youtube.com/watch?v=W81Qzuz4qH0
public class Heap<T extends Comparable<T>> {
    public List<T> items = new ArrayList<>();

    public void insert(T item) {
        items.add(item);
        siftup();
    }

    private void siftup() {
        int k = items.size() - 1; // current index
        while (k > 0) {
            int p = (k - 1) / 2;
            T item = items.get(k);
            T parent = items.get(p);

            if (item.compareTo(parent) > 0) {
                // swap
                items.set(k, parent);
                items.set(p, item);

                // move up one level
                k = p;
            } else {
                break;
            }
        }
    }

    public T delete() {
        T item = items.remove(0);
        if (items.size() > 0) {
            items.add(0, items.remove(items.size() - 1));
            siftDown();
        }
        return item;
    }

    private void siftDown() {
        int k = 0;
        int l = 2 * k + 1;
        while (l < items.size()) {
            int max = l, r = l + 1;
            if (r < items.size()) {
                if (items.get(r).compareTo(items.get(l)) > 0) {
                    max = r;
                }
            }

            if (items.get(k).compareTo(items.get(max)) < 0) {
                T temp = items.get(k);
                items.set(k, items.get(max));
                items.set(max, temp);

                // move down one level
                k = max;
                l = 2 * k + 1;
            } else {
                break;
            }
        }
    }
}
