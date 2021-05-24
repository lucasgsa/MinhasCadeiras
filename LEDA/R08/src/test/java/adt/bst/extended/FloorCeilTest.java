package adt.bst.extended;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FloorCeilTest {

	FloorCeilBSTImpl fc;
	
	@Before
	public void setUp() {
		fc = new FloorCeilBSTImpl();
	}
	
	@Test
	public void test0() {
		Integer[] array = {};
		assertNull(fc.ceil(array,-1));
	}
	
	@Test
	public void test1() {
		Integer[] array = {10};
		assertEquals(new Integer(10), fc.ceil(array,1));
	}
	
	@Test
	public void test2() {
		Integer[] array = {10};
		assertEquals(new Integer(10), fc.ceil(array,10));
	}
	
	@Test
	public void test3() {
		Integer[] array = {10};
		assertNull(fc.ceil(array,11));
	}
	
	@Test
	public void test4() {
		Integer[] array = {10,11};
		assertEquals(new Integer(11), fc.ceil(array,10.1));
	}
	
	@Test
	public void test5() {
		Integer[] array = {10};
		assertEquals(new Integer(10), fc.floor(array,12));
	}
	
	@Test
	public void test6() {
		Integer[] array = {10};
		assertEquals(new Integer(10), fc.floor(array,10));
	}
	
	@Test
	public void test7() {
		Integer[] array = {10,5};
		assertNull(fc.floor(array,4.9));
	}
	
	@Test
	public void test8() {
		Integer[] array = {};
		assertNull(fc.floor(array,-1));
	}
	

}
