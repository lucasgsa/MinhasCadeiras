package adt.linkedList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestRecursiveDoubleLinkedList {
	
	DoubleLinkedList<Integer> ll;
	
	@Before
	public void before() {
		ll = new RecursiveDoubleLinkedListImpl<>();
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
	
	@Test
	public void insertFirst1() {
		assertEquals(ll.size(), 0);
		assertTrue(ll.isEmpty());
		ll.insertFirst(1);
		assertEquals(ll.size(), 1);
		assertFalse(ll.isEmpty());
		ll.insertFirst(2);
		assertEquals(ll.size(), 2);
		assertFalse(ll.isEmpty());
	}
	
	@Test
	public void insertFirst2() {
		assertEquals(ll.size(), 0);
		assertTrue(ll.isEmpty());
		ll.insertFirst(1);
		assertEquals(ll.size(), 1);
		assertFalse(ll.isEmpty());
		
		Integer[] saida1 = {1};
		assertArrayEquals(ll.toArray(), saida1);
		
		ll.insertFirst(2);
		assertEquals(ll.size(), 2);
		assertFalse(ll.isEmpty());
		Integer[] saida2 = {2, 1};
		assertArrayEquals(ll.toArray(), saida2);
	}
	
	@Test
	public void removeFirst1() {
		ll.removeFirst();
		ll.insert(1);
		Integer[] saida1 = {1};
		assertArrayEquals(ll.toArray(), saida1);
		ll.removeFirst();
		Integer[] saida2 = {};
		assertArrayEquals(ll.toArray(), saida2);
	}
	
	@Test
	public void removeFirst2() {
		ll.removeFirst();
		ll.insert(1);
		ll.insert(2);
		Integer[] saida1 = {1,2};
		assertArrayEquals(ll.toArray(), saida1);
		ll.removeFirst();
		Integer[] saida2 = {2};
		assertArrayEquals(ll.toArray(), saida2);
	}
	
	@Test
	public void removeFirst3() {
		ll.removeFirst();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		Integer[] saida1 = {1,2,3};
		assertArrayEquals(ll.toArray(), saida1);
		ll.removeFirst();
		Integer[] saida2 = {2,3};
		assertArrayEquals(ll.toArray(), saida2);
	}

	@Test
	public void removeLast1() {
		ll.removeLast();
		ll.insert(1);
		ll.removeLast();
		Integer[] saida1 = {};
		assertArrayEquals(ll.toArray(), saida1);
	}
	
	@Test
	public void removeLast2() {
		ll.removeLast();
		ll.insert(1);
		ll.insert(2);
		ll.removeLast();
		Integer[] saida1 = {1};
		assertArrayEquals(ll.toArray(), saida1);
	}
	
	@Test
	public void removeLast3() {
		ll.removeLast();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.removeLast();
		Integer[] saida1 = {1,2};
		assertArrayEquals(ll.toArray(), saida1);
		ll.insert(3);
		ll.removeLast();
		ll.removeLast();
		Integer[] saida2 = {1};
		assertArrayEquals(ll.toArray(), saida2);
	}
	
	@Test
	public void removeLast4() {
		ll.removeLast();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.removeLast();
		Integer[] saida1 = {1,2};
		assertArrayEquals(ll.toArray(), saida1);
		ll.insert(3);
		ll.removeLast();
		ll.removeLast();
		ll.removeLast();
		Integer[] saida2 = {};
		assertArrayEquals(ll.toArray(), saida2);
		ll.removeLast();
	}
	
}
