package adt.heap;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import adt.heap.extended.FloorCeilHeapImpl;

public class FloorCeilTest {

	@Test
	public void testFloorMax() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		};
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(4), fc.floor(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.floor(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 9));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 129847));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, 0));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, -1));
	}

	@Test
	public void testFloorMin() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
			
		};
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(4), fc.floor(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.floor(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 9));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8,4,2,1}, 129847));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, 0));
		assertNull(fc.floor(new Integer[] {8,4,2,1}, -1));
	}
	
	@Test
	public void testFloorMin2() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
			
		};
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.floor(new Integer[] {8}, 8));
		assertEquals(new Integer(8), fc.floor(new Integer[] {8}, 9));
		assertNull(fc.floor(new Integer[] {8}, 7));
		
		assertNull(fc.floor(new Integer[] {}, 7));
	}
	
	@Test
	public void testCeilMax() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		};
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.ceil(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 6));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 7.9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 129847));
	}

	@Test
	public void testCeilMin() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
			
		};
		FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 5));
		assertEquals(new Integer(1), fc.ceil(new Integer[] {8,4,2,1}, 1));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 6));
		assertEquals(new Integer(8), fc.ceil(new Integer[] {8,4,2,1}, 7.9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 9));
		assertNull(fc.ceil(new Integer[] {8,4,2,1}, 129847));
	}
}
