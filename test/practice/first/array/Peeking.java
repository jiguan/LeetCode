package practice.first.array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

class PeekingIterator implements Iterator<Integer> {
	private Iterator<Integer> iterator;
	private Integer next = null;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		if(iterator!=null) {
			this.next = iterator.next();
		}
	}
	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return next;
	}

	@Override
	public boolean hasNext() {
		if (next == null)
			return false;
		return true;
	}

	@Override
	public Integer next() {
		Integer tmPeeking= next;
		this.next = iterator.hasNext() ? iterator.next() : null;
		return tmPeeking!= null ? tmPeeking: iterator.next();
	}
}

public class Peeking{
	@Test
	public void test1() {
		List<Integer> list = Arrays.asList(0, 1, 2);
		PeekingIterator iter = new PeekingIterator(list.iterator());
		assertEquals(Integer.valueOf(0), iter.next());
		assertEquals(Integer.valueOf(1), iter.peek());
		assertEquals(Integer.valueOf(1), iter.next());
		assertEquals(Integer.valueOf(2), iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void test2() {
		List<Integer> list = Arrays.asList(0);
		PeekingIterator iter = new PeekingIterator(list.iterator());
		assertTrue(iter.hasNext());
		assertEquals(Integer.valueOf(0), iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test3() {
		List<Integer> list = Arrays.asList(0);
		PeekingIterator iter = new PeekingIterator(list.iterator());
		iter.next();
		iter.next();
	}
}
