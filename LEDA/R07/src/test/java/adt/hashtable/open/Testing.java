package adt.hashtable.open;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Testing {

	@Test (expected = HashtableOverflowException.class)
	public void testCreate1() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(0, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(1));
	}
	
	public void testCreate2() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(0, HashFunctionClosedAddressMethod.DIVISION);
		assertEquals(-1, ht.indexOf(new HashtableElement(1)));
	}
	
	public void testCreate3() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(0, HashFunctionClosedAddressMethod.DIVISION);
		ht.remove(new HashtableElement(1));
		assertEquals(0, ht.size());
	}
	
	@Test
	public void testInsert1() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(10));
		ht.insert(new HashtableElement(11));
		ht.insert(new HashtableElement(12));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(14));
		ht.insert(new HashtableElement(15));
		ht.insert(new HashtableElement(16));
		ht.insert(new HashtableElement(17));
		ht.insert(new HashtableElement(18));
		assertEquals(9, ht.size());
		ht.insert(new HashtableElement(10));
		assertEquals(9, ht.size());
	}
	
	@Test
	public void testInsert2() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(10));
		ht.insert(new HashtableElement(11));
		ht.insert(new HashtableElement(12));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(14));
		ht.insert(new HashtableElement(15));
		ht.insert(new HashtableElement(16));
		ht.insert(new HashtableElement(17));
		ht.insert(new HashtableElement(18));
		ht.insert(new HashtableElement(10));
		ht.insert(new HashtableElement(19));
		assertEquals(10, ht.size());
		ht.insert(new HashtableElement(19));
		assertEquals(10, ht.size());
		// Atualizar elemento com tabela cheia.
	}
	
	@Test (expected = HashtableOverflowException.class)
	public void testInsert3() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(10));
		ht.insert(new HashtableElement(11));
		ht.insert(new HashtableElement(12));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(14));
		ht.insert(new HashtableElement(15));
		ht.insert(new HashtableElement(16));
		ht.insert(new HashtableElement(17));
		ht.insert(new HashtableElement(18));
		ht.insert(new HashtableElement(10));
		ht.insert(new HashtableElement(19));
		ht.insert(new HashtableElement(20));
	}
	
	@Test
	public void testRemove1() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.remove(new HashtableElement(1));
		ht.remove(new HashtableElement(2));
		assertEquals(0, ht.size());
	}
	
	@Test
	public void testRemove2() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(3));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(23));
		ht.remove(new HashtableElement(3));
		assertEquals(2, ht.size());
	}
	
	@Test
	public void testRemove3() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(3));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(23));
		ht.remove(new HashtableElement(3));
		assertEquals(null , ht.search(new HashtableElement(3)));
		assertEquals(new HashtableElement(13) , ht.search(new HashtableElement(13)));
		assertEquals(new HashtableElement(23) , ht.search(new HashtableElement(23)));
		assertEquals(4, ht.indexOf(new HashtableElement(13)));
		assertEquals(5, ht.indexOf(new HashtableElement(23)));
		assertEquals(2, ht.size());
		
		ht.insert(new HashtableElement(3));
		assertEquals(new HashtableElement(3) , ht.search(new HashtableElement(3)));
		assertEquals(new HashtableElement(13) , ht.search(new HashtableElement(13)));
		assertEquals(new HashtableElement(23) , ht.search(new HashtableElement(23)));
		assertEquals(3, ht.indexOf(new HashtableElement(3)));
		assertEquals(4, ht.indexOf(new HashtableElement(13)));
		assertEquals(5, ht.indexOf(new HashtableElement(23)));
	}
	
	@Test
	public void testIndexOf1() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(3));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(23));
		ht.insert(new HashtableElement(33));
		ht.insert(new HashtableElement(43));
		ht.insert(new HashtableElement(53));
		ht.insert(new HashtableElement(63));
		ht.insert(new HashtableElement(73));
		ht.insert(new HashtableElement(83));
		ht.insert(new HashtableElement(93));
		assertEquals(3, ht.indexOf(new HashtableElement(3)));
		assertEquals(4, ht.indexOf(new HashtableElement(13)));
		assertEquals(5, ht.indexOf(new HashtableElement(23)));
		assertEquals(6, ht.indexOf(new HashtableElement(33)));
		assertEquals(7, ht.indexOf(new HashtableElement(43)));
		assertEquals(8, ht.indexOf(new HashtableElement(53)));
		assertEquals(9, ht.indexOf(new HashtableElement(63)));
		assertEquals(0, ht.indexOf(new HashtableElement(73)));
		assertEquals(1, ht.indexOf(new HashtableElement(83)));
		assertEquals(2, ht.indexOf(new HashtableElement(93)));
	}
	
	@Test
	public void testIndexOf2() {
		AbstractHashtableOpenAddress<HashtableElement> ht = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(10, HashFunctionClosedAddressMethod.DIVISION);
		ht.insert(new HashtableElement(3));
		ht.insert(new HashtableElement(13));
		ht.insert(new HashtableElement(23));
		ht.insert(new HashtableElement(33));
		ht.insert(new HashtableElement(43));
		ht.insert(new HashtableElement(53));
		ht.insert(new HashtableElement(63));
		ht.insert(new HashtableElement(73));
		ht.insert(new HashtableElement(83));
		ht.insert(new HashtableElement(93));
		ht.remove(new HashtableElement(73));
		assertEquals(3, ht.indexOf(new HashtableElement(3)));
		assertEquals(4, ht.indexOf(new HashtableElement(13)));
		assertEquals(5, ht.indexOf(new HashtableElement(23)));
		assertEquals(6, ht.indexOf(new HashtableElement(33)));
		assertEquals(7, ht.indexOf(new HashtableElement(43)));
		assertEquals(8, ht.indexOf(new HashtableElement(53)));
		assertEquals(9, ht.indexOf(new HashtableElement(63)));
		assertEquals(-1, ht.indexOf(new HashtableElement(73)));
		assertEquals(1, ht.indexOf(new HashtableElement(83)));
		assertEquals(2, ht.indexOf(new HashtableElement(93)));
	}

}
