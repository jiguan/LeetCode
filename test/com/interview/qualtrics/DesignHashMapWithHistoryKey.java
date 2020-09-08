package com.interview.qualtrics;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DesignHashMapWithHistoryKey {


    @Test
    public void test0() {
        HashMap map = new HashMap();
        map.set(1, 101);
        map.set(11, 1011);

        assertEquals(Integer.valueOf(101), map.get(1));
        assertEquals(Integer.valueOf(1011), map.get(11));
        map.get(11);
        assertEquals(Integer.valueOf(11), map.getLastAccessKey());
        map.delete(11);
        assertEquals(Integer.valueOf(1), map.getLastAccessKey());
        map.delete(1);
        assertEquals(null, map.getLastAccessKey());
    }



    class HashMap {

        int size = 0;
        int capacity = 10;
        Entry[] buckets = new Entry[capacity];
        Entry head = new Entry(-1, -1);

        HashMap() {
            for (int i = 0; i < capacity; ++i) {
                buckets[i] = new Entry(-1, -1);
            }
        }

        Integer get(int key) {
            Entry pre = findEntryWhoseNextIsTarget(key);
            if (pre.next == null)
                return null;
            updateAccess(pre.next);
            return pre.next.val;
        }

        void deleteAccess(Entry entry) {
            if (entry.accessPre != null) {
                entry.accessPre.accessNext = entry.accessNext;
            }
            if (entry.accessNext != null) {
                entry.accessNext.accessPre = entry.accessPre;
            }
            entry.accessNext = null;
            entry.accessPre = null;
        }

        void updateAccess(Entry entry) {
            deleteAccess(entry);
            Entry next = head.accessNext;
            head.accessNext = entry;
            entry.accessPre = head;
            entry.accessNext = next;
            if (next != null) {
                next.accessPre = entry;
            }
        }

        private Entry findEntryWhoseNextIsTarget(int key) {
            int bucket = key % 10;
            Entry pre = buckets[bucket];
            while (pre.next != null && pre.next.key != key) {
                pre = pre.next;
            }
            return pre;
        }

        void set(int key, int val) {
            Entry pre = findEntryWhoseNextIsTarget(key);
            if (pre.next == null) {
                pre.next = new Entry(key, val);
            } else {
                pre.next.val = val;
            }
            updateAccess(pre.next);
        }

        void delete(int key) {
            Entry pre = findEntryWhoseNextIsTarget(key);
            if (pre.next == null)
                return;
            Entry node = pre.next;
            deleteAccess(node);
            pre.next = node.next;
            if (node.next != null) {
                node.next.pre = pre;
            }
        }

        // return the last get/set key
        Integer getLastAccessKey() {
            if (head.accessNext == null)
                return null;
            return head.accessNext.key;
        }
    }


    class Entry {
        Entry pre, next;
        Entry accessPre, accessNext;
        int key, val;

        Entry(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return key + ":" + val;
        }
    }

}


