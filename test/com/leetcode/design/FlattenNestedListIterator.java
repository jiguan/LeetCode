package com.leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Test;
import com.leetcode.util.NestedInteger;

public class FlattenNestedListIterator {
    private Queue<Integer> queue = new LinkedList<>();

    public void build(List<NestedInteger> nestedList) {
        for (NestedInteger i : nestedList) {
            parse(i);
        }
    }

    private void parse(NestedInteger integer) {
        if (integer.isInteger()) {
            queue.offer(integer.getInteger());
        }
        for (NestedInteger i : integer.getList()) {
            parse(i);
        }
    }

    public Integer next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Test
    public void test0() {
        // [[1,1],2,[1,1]]
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger(Arrays.asList(new NestedInteger(new Integer(1)),
                new NestedInteger(new Integer(1)))));
        nestedList.add(new NestedInteger(new Integer(2)));
        nestedList.add(new NestedInteger(Arrays.asList(new NestedInteger(new Integer(1)),
                new NestedInteger(new Integer(1)))));
        FlattenNestedListIterator iter = new FlattenNestedListIterator();
        iter.build(nestedList);
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }
}
