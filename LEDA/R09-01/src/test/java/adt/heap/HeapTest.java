package adt.heap;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class HeapTest {

	@Test
	public void testSortMax() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		assertArrayEquals(new Integer[] { 3,3,4,4,5,10 },
				hp.heapsort(new Integer[] { 5,4,3,10,3,4 }));
		assertArrayEquals(new Integer[] { 5 },
				hp.heapsort(new Integer[] { 5 }));
		assertArrayEquals(new Integer[] { 4, 5 },
				hp.heapsort(new Integer[] { 5, 4 }));
		assertArrayEquals(new Integer[] { 2,4,5 },
				hp.heapsort(new Integer[] { 5,4,2 }));
		assertArrayEquals(new Integer[] { },
				hp.heapsort(new Integer[] { }));
	}
	
	@Test
	public void testSortMin() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		assertArrayEquals(new Integer[] { 3,3,4,4,5,10 },
				hp.heapsort(new Integer[] { 5,4,3,10,3,4 }));
		assertArrayEquals(new Integer[] { 5 },
				hp.heapsort(new Integer[] { 5 }));
		assertArrayEquals(new Integer[] { 4, 5 },
				hp.heapsort(new Integer[] { 5, 4 }));
		assertArrayEquals(new Integer[] { 2,4,5 },
				hp.heapsort(new Integer[] { 5,4,2 }));
		assertArrayEquals(new Integer[] { },
				hp.heapsort(new Integer[] { }));
	}
	
	@Test
	public void testBuildMin1() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		assertNull(hp.extractRootElement());
		assertNull(hp.rootElement());
	}
	
	@Test
	public void testBuildMax1() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		assertNull(hp.extractRootElement());
		assertNull(hp.rootElement());
	}
	
	@Test
	public void testBuildMin2() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		hp.buildHeap(new Integer[] {});
		assertNull(hp.extractRootElement());
		assertNull(hp.rootElement());
	}
	
	@Test
	public void testBuildMax2() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		hp.buildHeap(new Integer[] {});
		assertNull(hp.extractRootElement());
		assertNull(hp.rootElement());
	}
	
	@Test
	public void testBuildMin3() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		hp.buildHeap(new Integer[] {1});
		assertEquals(new Integer(1), hp.rootElement());
		assertEquals(new Integer(1), hp.extractRootElement());
	}
	
	@Test
	public void testBuildMax3() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		hp.buildHeap(new Integer[] {1});
		assertEquals(new Integer(1), hp.rootElement());
		assertEquals(new Integer(1), hp.extractRootElement());
	}
	
	@Test
	public void testBuildMin4() {
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		hp.insert(1);
		assertEquals(new Integer(1), hp.extractRootElement());
		hp.insert(3);
		hp.insert(2);
		hp.insert(1);
		assertEquals(new Integer(1), hp.extractRootElement());
		assertEquals(new Integer(2), hp.extractRootElement());
		assertEquals(new Integer(3), hp.extractRootElement());
		assertNull(hp.extractRootElement());
		assertNull(hp.rootElement());
	}
	
	@Test
	public void testBuildMax4() {
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		hp.insert(1);
		assertEquals(new Integer(1), hp.extractRootElement());
		hp.insert(3);
		hp.insert(2);
		hp.insert(1);
		assertEquals(new Integer(3), hp.extractRootElement());
		assertEquals(new Integer(2), hp.extractRootElement());
		assertEquals(new Integer(1), hp.extractRootElement());
		assertNull(hp.extractRootElement());
		assertNull(hp.rootElement());
	}

}
