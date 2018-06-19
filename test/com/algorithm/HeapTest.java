package com.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeapTest {
    @Test
    public void testInsert() {
        Heap<Integer> heap = new Heap<>();
        for (Integer i = 0; i < 10; i++) {
            heap.insert(i);
            assertEquals(heap.items.get(0), i);
        }

        for (Integer i = 9; i >= 0; i--) {
            assertEquals(heap.delete(), i);
        }
    }
}
