package adt.heap;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import adt.heap.extended.FloorCeilHeapImpl;

public class FloorCeilTest {

	@Test
	public void testFloorMax() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(4), fc.floor(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.floor(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 9));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,8,4,4,2,2,1,1}, 9));
		assertEquals(new Integer(4), fc.floor(new Integer[] {10,8,8,4,4,2,1}, 7));
		assertEquals(new Integer(10), fc.floor(new Integer[] {10,10}, 11));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 129847));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, 0));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, -1));
		assertNull(fc.floor(new Integer[] {10,10}, 9));
	}
	
	@Test
	public void testFloorMax2() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.floor(new Integer[] {8}, 8));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8}, 9));
		assertNull(fc.floor(new Integer[] {8}, 7));
		
		assertNull(fc.floor(new Integer[] {}, 7));
	}

	@Test
	public void testFloorMin() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(4), fc.floor(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.floor(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 9));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,8,4,4,2,2,1,1}, 9));
		assertEquals(new Integer(4), fc.floor(new Integer[] {10,8,8,4,4,2,1}, 7));
		assertEquals(new Integer(10), fc.floor(new Integer[] {10,10}, 11));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 129847));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, 0));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, -1));
		assertNull(fc.floor(new Integer[] {10,10}, 9));
	}
	
	@Test
	public void testFloorMin2() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.floor(new Integer[] {8}, 8));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8}, 9));
		assertNull(fc.floor(new Integer[] {8}, 7));
		
		assertNull(fc.floor(new Integer[] {}, 7));
	}
	
	@Test
	public void testCeilMax() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.ceil(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 6));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,8,4,4,2,2,1,1}, 5));
		assertEquals(new Integer(2), fc.ceil(new Integer[] {10,8,8,4,4,2,1}, 2));
		assertEquals(new Integer(10), fc.ceil(new Integer[] {10,10}, -1));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 7.9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 129847));
		assertNull(fc.ceil(new Integer[] {10,10}, 11));
		assertEquals(new Integer(10), fc.ceil(new Integer[] {10,10, 9}, 9.1));
	}
	
	@Test
	public void testCeilMax2() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8}, 8));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8}, 7));
		assertNull(fc.ceil(new Integer[] {8}, 9));
		
		assertNull(fc.ceil(new Integer[] {}, 7));
	}

	@Test
	public void testCeilMin() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.ceil(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 6));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,8,4,4,2,2,1,1}, 5));
		assertEquals(new Integer(2), fc.ceil(new Integer[] {10,8,8,4,4,2,1}, 2));
		assertEquals(new Integer(10), fc.ceil(new Integer[] {10,10}, -1));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 7.9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 129847));
		assertNull(fc.ceil(new Integer[] {10,10}, 11));
		assertEquals(new Integer(10), fc.ceil(new Integer[] {10,10, 9}, 9.1));
	}
	
	@Test
	public void testCeilMin2() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8}, 8));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8}, 7));
		assertNull(fc.ceil(new Integer[] {8}, 9));
		
		assertNull(fc.ceil(new Integer[] {}, 7));
	}
}
