package adt.heap;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class HeapTest {

	@Test
	public void testBuild() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		};
		HeapImpl<Integer> hp = new HeapImpl<>(comparator);
		assertArrayEquals(new Integer[] { 5 },
				hp.heapsort(new Integer[] { 5 }));
		assertArrayEquals(new Integer[] { 4, 5 },
				hp.heapsort(new Integer[] { 5, 4 }));
		assertArrayEquals(new Integer[] { 2,4,5 },
				hp.heapsort(new Integer[] { 5,4,2 }));
		assertArrayEquals(new Integer[] { },
				hp.heapsort(new Integer[] { }));
	}

}
