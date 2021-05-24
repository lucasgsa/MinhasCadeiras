package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {

	protected LinkedList<Integer> lista1;
	protected LinkedList<Integer> lista2;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveSingleLinkedListImpl<>();
		lista2 = new RecursiveSingleLinkedListImpl<>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(2 == lista1.search(2));
		Assert.assertNull(lista1.search(4));
		Assert.assertFalse(3 == lista1.search(2));
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(3, lista1.size());
		lista1.insert(5);
		lista1.insert(7);
		Assert.assertEquals(5, lista1.size());

		Assert.assertEquals(0, lista2.size());
		lista2.insert(4);
		lista2.insert(7);
		Assert.assertEquals(2, lista2.size());
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());

	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
	}
	
	// Testes lista2
	
	@Test
	public void testLista2() {
		lista2.insert(7);
		lista2.remove(8);
		Assert.assertEquals(1, lista2.size());
		Assert.assertFalse(lista2.isEmpty());
		
		lista2.remove(7);
		Assert.assertEquals(0, lista2.size());
		Assert.assertNull(lista2.search(0));
		Assert.assertNull(lista2.search(-1));
		Assert.assertNull(lista2.search(null));
		Assert.assertTrue(lista2.isEmpty());
		
		lista2.remove(3);
		
		Assert.assertEquals(0, lista2.size());
		
		lista2.insert(null);
		
		Assert.assertEquals(0, lista2.size());
		
		lista2.insert(null);
		
		Assert.assertTrue(lista2.isEmpty());
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		
		lista2.insert(new Integer(9));
		lista2.insert(null);
		
		Assert.assertFalse(lista2.isEmpty());
		
		lista2.insert(new Integer(8));
		
		Assert.assertArrayEquals(new Integer[] {9, 8}, lista2.toArray());
		Assert.assertFalse(lista2.isEmpty());
		
		lista2.remove(new Integer(8));
		Assert.assertArrayEquals(new Integer[] {9}, lista2.toArray());

		lista2.insert(new Integer(10));
		lista2.insert(null);
		lista2.insert(new Integer(11));
		lista2.insert(null);
		lista2.insert(new Integer(12));
		lista2.remove(null);
		
		Assert.assertTrue(new Integer(9).equals(lista2.search(9)));
		Assert.assertTrue(new Integer(10).equals(lista2.search(10)));
		Assert.assertTrue(new Integer(11).equals(lista2.search(11)));
		Assert.assertTrue(new Integer(12).equals(lista2.search(12)));
		Assert.assertNull(lista2.search(77));
		Assert.assertNull(lista2.search(null));
		Assert.assertArrayEquals(new Integer[] {9, 10, 11, new Integer(12)}, lista2.toArray());
		
		lista2.remove(9);
		
		Assert.assertArrayEquals(new Integer[] {10, 11, new Integer(12)}, lista2.toArray());
		
		lista2.remove(12);
		
		Assert.assertArrayEquals(new Integer[] {10, 11}, lista2.toArray());
		
		lista2.remove(10);
		lista2.remove(new Integer(11));
		
		Assert.assertTrue(lista2.isEmpty());
		Assert.assertEquals(0, lista2.size());
		Assert.assertNull(lista2.search(null));
		
		lista2.insert(6);
		lista2.insert(7);
		lista2.insert(15);
		lista2.insert(20);
		lista2.insert(15);
		lista2.insert(22);
		
		lista2.remove(50);
		lista2.remove(100);
		
		Assert.assertTrue(new Integer(15).equals(lista2.search(15)));
		Assert.assertTrue(new Integer(6).equals(lista2.search(6)));
		Assert.assertTrue(new Integer(22).equals(lista2.search(22)));
		Assert.assertNull(lista2.search(50));
		Assert.assertNull(lista2.search(0));
		Assert.assertNull(lista2.search(-1));
		Assert.assertNull(lista2.search(null));
		
		Assert.assertEquals(6, lista2.size());
		Assert.assertArrayEquals(new Integer[] {6, 7, 15, 20, 15, 22}, lista2.toArray());
		
		lista2.remove(15);
		
		Assert.assertEquals(5, lista2.size());
		Assert.assertArrayEquals(new Integer[] {6, 7, 20, 15, 22}, lista2.toArray());		
	}

}