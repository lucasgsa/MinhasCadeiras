package adt.stack;
import static org.junit.Assert.*;

import org.junit.Test;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class StackTests {

	@Test
	public void testAdd1() throws StackOverflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		st.push(2);
		st.push(3);
	}
	
	@Test (expected = StackOverflowException.class)
	public void testAdd2() throws StackOverflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(0);
		st.push(1);
	}
	
	@Test (expected = StackOverflowException.class)
	public void testAdd3() throws StackOverflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
	}
	
	@Test
	public void testTop1() {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		assertNull(st.top());
	}
	
	@Test
	public void testTop2() throws StackOverflowException, StackUnderflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		st.push(2);
		st.push(3);
		st.pop();
		st.pop();
		st.pop();
		assertNull(st.top());
	}
	
	@Test
	public void testTop3() {
		StackImpl<Integer> st = new StackImpl<Integer>(0);
		assertNull(st.top());
	}
	
	@Test
	public void testTop4() throws StackOverflowException, StackUnderflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		assertEquals(st.top(), new Integer(1));
		st.push(2);
		assertEquals(st.top(), new Integer(2));
		st.pop();
		assertEquals(st.top(), new Integer(1));
	}
	
	@Test
	public void testPop1() throws StackOverflowException, StackUnderflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		assertEquals(st.pop(), new Integer(1));
		st.push(2);
		st.push(3);
		st.push(4);
		assertEquals(st.pop(), new Integer(4));
		assertEquals(st.pop(), new Integer(3));
		assertEquals(st.pop(), new Integer(2));
	}
	
	@Test (expected = StackUnderflowException.class)
	public void testPop2() throws StackOverflowException, StackUnderflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		assertEquals(st.pop(), new Integer(1));
		st.pop();
	}
	
	@Test (expected = StackUnderflowException.class)
	public void testPop3() throws StackOverflowException, StackUnderflowException {
		StackImpl<Integer> st = new StackImpl<Integer>(3);
		st.push(1);
		st.push(2);
		st.push(3);
		assertEquals(st.pop(), new Integer(3));
		assertEquals(st.pop(), new Integer(2));
		assertEquals(st.pop(), new Integer(1));
		st.pop();
	}

}
