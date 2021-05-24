package adt.bst;

import static org.junit.Assert.*;

import org.junit.Test;

public class ManipulationTest {

	private BST<Integer> getTree(Integer[] array){
		BSTImpl<Integer> bst = new BSTImpl<Integer>();
		for (Integer element: array) {
			bst.insert(element);
		}
		return bst;
	}
	
	@Test
	public void isSimilarTest1() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {};
		Integer[] a2 = {};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertTrue(sm.isSimilar(bst1, bst2));
	}
	
	@Test
	public void isSimilarTest2() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {10, 5, 1, 4, 2, -10};
		Integer[] a2 = {11, 6, 2, 5, 3, -9};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertTrue(sm.isSimilar(bst1, bst2));
	}
	
	@Test
	public void isSimilarTest3() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {5, 10, 1, 4, 2, -10};
		Integer[] a2 = {10, 5, 1, 4, 2, -10};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertFalse(sm.isSimilar(bst1, bst2));
	}
	
	@Test
	public void isSimilarTest4() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {1};
		Integer[] a2 = {2};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertTrue(sm.isSimilar(bst1, bst2));
	}
	
	@Test
	public void equalsTest1() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {};
		Integer[] a2 = {};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertTrue(sm.equals(bst1, bst2));
	}
	
	@Test
	public void equalsTest2() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {1,2};
		Integer[] a2 = {1,3};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertFalse(sm.equals(bst1, bst2));
	}
	
	@Test
	public void equalsTest3() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {5, 10, 1, 4, 2, -10};
		Integer[] a2 = {10, 5, 1, 4, 2, -10};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertFalse(sm.equals(bst1, bst2));
	}
	
	@Test
	public void equalsTest4() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {10, 5, 15};
		Integer[] a2 = {10, 5, 15, 20};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertFalse(sm.equals(bst1, bst2));
	}
	
	@Test
	public void equalsTest5() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {10, 5, 15};
		Integer[] a2 = {};
		BST<Integer> bst1 = getTree(a1);
		BST<Integer> bst2 = getTree(a2);
		assertFalse(sm.equals(bst1, bst2));
	}
	
	@Test
	public void orderETest1() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {10, 5, 15};
		BST<Integer> bst1 = getTree(a1);
		assertEquals(new Integer(5), sm.orderStatistic(bst1, 1));
		assertEquals(new Integer(10), sm.orderStatistic(bst1, 2));
		assertEquals(new Integer(15), sm.orderStatistic(bst1, 3));
		assertNull(sm.orderStatistic(bst1, 4));
		assertNull(sm.orderStatistic(bst1, 5));
	}
	
	@Test
	public void orderETest2() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {10, 5, 15, 25, 12, 13};
		BST<Integer> bst1 = getTree(a1);
		assertEquals(new Integer(13), sm.orderStatistic(bst1, 4));
	}
	
	@Test
	public void orderETest3() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {};
		BST<Integer> bst1 = getTree(a1);
		assertNull(sm.orderStatistic(bst1, 10));
	}
	
	@Test
	public void orderETest4() {
		SimpleBSTManipulationImpl<Integer> sm = new SimpleBSTManipulationImpl<Integer>(); 
		Integer[] a1 = {10, 5, 15, 25, 12, 13};
		BST<Integer> bst1 = getTree(a1);
		assertNull(sm.orderStatistic(bst1, 0));
	}

}
