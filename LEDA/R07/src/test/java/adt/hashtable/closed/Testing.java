package adt.hashtable.closed;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Testing {

	@Test
	public void test() {
		AbstractHashtableClosedAddress<Integer> table1 = new HashtableClosedAddressImpl<Integer>(0,
				HashFunctionClosedAddressMethod.DIVISION);
		table1.insert(1);
		table1.search(1);
		table1.remove(1);
	}
	
	@Test
	public void testInsert1() {
		AbstractHashtableClosedAddress<Integer> table1 = new HashtableClosedAddressImpl<Integer>(10,
				HashFunctionClosedAddressMethod.DIVISION);
		table1.insert(1);
		assertEquals(1, table1.size());
		table1.insert(1);
		assertEquals(1, table1.size());
	}
	
	@Test
	public void testInsert2() {
		AbstractHashtableClosedAddress<Integer> table1 = new HashtableClosedAddressImpl<Integer>(10,
				HashFunctionClosedAddressMethod.DIVISION);
		table1.insert(2);
		table1.insert(10);
		assertEquals(2, table1.size());
		table1.insert(1);
		table1.insert(21);
		assertEquals(4, table1.size());
		
		assertEquals(1, table1.indexOf(1));
		assertEquals(2, table1.indexOf(2));
		assertEquals(10, table1.indexOf(10));
	}
	
	@Test
	public void testInsert3() {
		AbstractHashtableClosedAddress<Integer> table1 = new HashtableClosedAddressImpl<Integer>(10,
				HashFunctionClosedAddressMethod.DIVISION);
		table1.insert(2);
		table1.insert(10);
		table1.insert(1);
		table1.insert(21);
		assertEquals(4, table1.size());
		table1.insert(10);
		table1.insert(21);
		assertEquals(4, table1.size());
	}
	
	@Test
	public void testRemove1() {
		AbstractHashtableClosedAddress<Integer> table1 = new HashtableClosedAddressImpl<Integer>(10,
				HashFunctionClosedAddressMethod.DIVISION);
		table1.remove(1);
		assertEquals(0, table1.size());
		table1.insert(1);
		table1.insert(12);
		table1.insert(23);
		assertEquals(3, table1.size());
		table1.remove(12);
		assertEquals(2, table1.size());
		table1.remove(1);
		assertEquals(-1, table1.indexOf(1));
		assertEquals(1, table1.indexOf(23));
		table1.remove(23);
		assertEquals(-1, table1.indexOf(23));
	}

}
