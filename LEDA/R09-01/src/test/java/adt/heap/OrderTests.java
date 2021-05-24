package adt.heap;

import static org.junit.Assert.*;

import org.junit.Test;

import orderStatistic.OrderStatisticsHeapImpl;

public class OrderTests {

	@Test
	public void test() {
		OrderStatisticsHeapImpl<Integer> or = new OrderStatisticsHeapImpl<>();
		assertEquals(new Integer(1), or.getOrderStatistics(new Integer[] {1,2,3,4}, 1));
		assertEquals(new Integer(1), or.getOrderStatistics(new Integer[] {4,3,2,1}, 1));
		assertEquals(new Integer(3), or.getOrderStatistics(new Integer[] {1,2,3,4}, 3));
		assertEquals(new Integer(3), or.getOrderStatistics(new Integer[] {4,3,2,1}, 3));
		assertEquals(new Integer(4), or.getOrderStatistics(new Integer[] {1,2,3,4}, 4));
		assertEquals(new Integer(4), or.getOrderStatistics(new Integer[] {4,3,2,1}, 4));
		
		assertEquals(new Integer(1), or.getOrderStatistics(new Integer[] {1}, 1));
		assertNull(or.getOrderStatistics(new Integer[] {1}, 0));
		assertNull(or.getOrderStatistics(new Integer[] {4,3,2,1}, 0));
		assertNull(or.getOrderStatistics(new Integer[] {1}, 2));
	}

}
