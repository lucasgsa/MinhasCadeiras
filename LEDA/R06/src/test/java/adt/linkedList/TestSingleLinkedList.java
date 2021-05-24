package adt.linkedList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestSingleLinkedList {
	
	LinkedList<Integer> ll;
	
	@Before
	public void before() {
		ll = new SingleLinkedListImpl<>();
	}
	
	@Test
	public void isEmpty() {
		assertTrue(ll.isEmpty());
		ll.insert(1);
		assertFalse(ll.isEmpty());
		ll.insert(2);
		assertFalse(ll.isEmpty());
		ll.remove(1);
		ll.remove(2);
		assertTrue(ll.isEmpty());
	}
	
	@Test
	public void size() {
		assertEquals(ll.size(), 0);
		ll.insert(1);
		assertEquals(ll.size(), 1);
		ll.remove(1);
		assertEquals(ll.size(), 0);
		ll.insert(2);
		assertEquals(ll.size(), 1);
		ll.insert(1);
		assertEquals(ll.size(), 2);
		ll.remove(3);
		assertEquals(ll.size(), 2);
	}
	
	@Test
	public void search() {
		assertEquals(ll.search(1), null);
		assertEquals(ll.search(null), null);
		ll.insert(1);
		assertEquals(ll.search(null), null);
		assertEquals(ll.search(1), new Integer(1));
		assertEquals(ll.search(2), null);
		ll.remove(1);
		assertEquals(ll.search(1), null);
	}
	
	@Test
	public void insert() {
		assertEquals(ll.size(), 0);
		Integer[] saida1 = {};
		assertArrayEquals(ll.toArray(), saida1);
		
		
		ll.insert(1);
		
		assertEquals(ll.size(), 1);
		Integer[] saida2 = {1};
		assertArrayEquals(ll.toArray(), saida2);
		
		ll.insert(2);
		
		assertEquals(ll.size(), 2);
		Integer[] saida3 = {1,2};
		assertArrayEquals(ll.toArray(), saida3);
		
		ll.insert(3);
		
		assertEquals(ll.size(), 3);
		Integer[] saida4 = {1,2,3};
		assertArrayEquals(ll.toArray(), saida4);
		
		ll.remove(4);
		assertEquals(ll.size(), 3);
		Integer[] saida5 = {1,2,3};
		assertArrayEquals(ll.toArray(), saida5);
		
		ll.remove(3);
		assertEquals(ll.size(), 2);
		assertArrayEquals(ll.toArray(), saida3);
		
		ll.remove(2);
		assertEquals(ll.size(), 1);
		assertArrayEquals(ll.toArray(), saida2);
		
		ll.remove(1);
		assertEquals(ll.size(), 0);
		assertArrayEquals(ll.toArray(), saida1);
		
		ll.remove(1);
		assertEquals(ll.size(), 0);
		assertArrayEquals(ll.toArray(), saida1);
		
		
		ll.insert(1);
		
		assertEquals(ll.size(), 1);
		assertArrayEquals(ll.toArray(), saida2);
		
		ll.insert(2);
		
		assertEquals(ll.size(), 2);
		assertArrayEquals(ll.toArray(), saida3);
		
		ll.insert(3);
		
		assertEquals(ll.size(), 3);
		assertArrayEquals(ll.toArray(), saida4);
	}
	
	@Test
	public void remove1() {
		ll.remove(1);
		ll.insert(1);
		ll.remove(1);
		assertEquals(ll.size(), 0);
	}
	
	@Test
	public void remove2() {
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.remove(3);
		assertEquals(ll.size(), 2);
	}
	
	@Test
	public void remove3() {
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.remove(4);
		assertEquals(ll.size(), 3);
	}

}
