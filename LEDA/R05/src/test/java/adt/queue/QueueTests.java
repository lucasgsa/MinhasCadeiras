package adt.queue;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.stack.StackOverflowException;

public class QueueTests {

	@Test
	public void testAdd1() throws QueueOverflowException {
		Queue<Integer> q = new CircularQueue<Integer>(3);
		q.enqueue(new Integer(1));
		q.enqueue(new Integer(2));
		q.enqueue(new Integer(3));
	}
	
	@Test (expected = QueueOverflowException.class)
	public void testAdd2() throws QueueOverflowException {
		Queue<Integer> q = new CircularQueue<Integer>(3);
		q.enqueue(new Integer(1));
		q.enqueue(new Integer(2));
		q.enqueue(new Integer(3));
		q.enqueue(new Integer(4));
	}
	
	@Test (expected = QueueOverflowException.class)
	public void testAdd3() throws QueueOverflowException {
		Queue<Integer> q = new CircularQueue<Integer>(0);
		q.enqueue(new Integer(1));
	}
	
	@Test
	public void testAdd4() throws QueueOverflowException, QueueUnderflowException {
		Queue<Integer> q = new CircularQueue<Integer>(3);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.dequeue();
		q.enqueue(4);
	}
	
	@Test
	public void testHead1() {
		Queue<Integer> q = new CircularQueue<Integer>(3);
		assertNull(q.head());
	}
	
	@Test
	public void testHead2() {
		Queue<Integer> q = new CircularQueue<Integer>(0);
		assertNull(q.head());
	}
	
	@Test
	public void testHead3() throws QueueOverflowException, QueueUnderflowException {
		Queue<Integer> q = new CircularQueue<Integer>(3);
		q.enqueue(1);
		assertEquals(q.head(), new Integer(1));
		q.enqueue(2);
		assertEquals(q.head(), new Integer(1));
		q.enqueue(3);
		assertEquals(q.head(), new Integer(1));
		assertEquals(q.dequeue(), new Integer(1));
		assertEquals(q.head(), new Integer(2));
		assertEquals(q.dequeue(), new Integer(2));
		assertEquals(q.head(), new Integer(3));
		assertEquals(q.dequeue(), new Integer(3));
		assertNull(q.head());
		q.enqueue(4);
		assertEquals(q.dequeue(), new Integer(4));
	}
	
	@Test
	public void testRemove1() throws QueueOverflowException, QueueUnderflowException {
		Queue<Integer> q = new CircularQueue<Integer>(3);
		q.enqueue(1);
		assertEquals(q.dequeue(), new Integer(1));
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		assertTrue(q.isFull());
		assertEquals(q.dequeue(), new Integer(2));
		assertEquals(q.dequeue(), new Integer(3));
		assertEquals(q.dequeue(), new Integer(4));
		q.enqueue(4);
		q.enqueue(6);
		assertEquals(q.dequeue(), new Integer(4));
		assertEquals(q.dequeue(), new Integer(6));
	}
	
	@Test (expected = QueueUnderflowException.class)
	public void testRemove2() throws QueueUnderflowException {
		Queue<Integer> q = new CircularQueue<Integer>(0);
		q.dequeue();
	}

}
