package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;
	private DoubleLinkedList<String> listaStringDouble;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListImpl<>();
		lista2 = new DoubleLinkedListImpl<>();
		lista3 = new DoubleLinkedListImpl<>();
		listaStringDouble = new DoubleLinkedListImpl<String>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}
	
	// Test Lista String Double
	@SuppressWarnings("unchecked")
	@Test
	public void testListaStringDouble() {
		Assert.assertEquals(0, listaStringDouble.size());
		Assert.assertEquals("NIL", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("NIL", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		
		listaStringDouble.insert("Raphael");
		listaStringDouble.insert("Joao");
		listaStringDouble.insert("Sophia");
		listaStringDouble.insert(null);
		listaStringDouble.insertFirst(null);
		
		Assert.assertNull(listaStringDouble.search("oi"));
		Assert.assertEquals("Joao", listaStringDouble.search("Joao"));
		Assert.assertEquals("Raphael", listaStringDouble.search("Raphael"));
		Assert.assertEquals("Sophia", listaStringDouble.search("Sophia"));
		Assert.assertEquals(3, listaStringDouble.size());
		
		listaStringDouble.insertFirst("irineu");
		Assert.assertArrayEquals(new String[] { "irineu", "Raphael", "Joao", "Sophia" }, listaStringDouble.toArray());
		Assert.assertEquals("irineu", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("Sophia", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeFirst();
		Assert.assertArrayEquals(new String[] { "Raphael", "Joao", "Sophia" }, listaStringDouble.toArray());
		Assert.assertEquals(3, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertEquals("Raphael", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("Sophia", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeFirst();
		Assert.assertArrayEquals(new String[] { "Joao", "Sophia" }, listaStringDouble.toArray());
		Assert.assertEquals(2, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertEquals("Joao", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("Sophia", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeFirst();
		Assert.assertArrayEquals(new String[] { "Sophia" }, listaStringDouble.toArray());
		Assert.assertEquals(1, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertEquals("Sophia", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("Sophia", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeFirst();
		Assert.assertArrayEquals(new String[] { }, listaStringDouble.toArray());
		Assert.assertEquals(0, listaStringDouble.size());
		Assert.assertTrue(listaStringDouble.isEmpty());
		Assert.assertEquals("NIL", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("NIL", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insert("a");
		Assert.assertEquals("a", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("a", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insertFirst("b");
		Assert.assertArrayEquals(new String[] {"b", "a" }, listaStringDouble.toArray());
		Assert.assertEquals("b", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("a", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insert("c");
		Assert.assertArrayEquals(new String[] {"b", "a", "c" }, listaStringDouble.toArray());
		Assert.assertEquals("b", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("c", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insert("d");
		listaStringDouble.insert("e");
		listaStringDouble.insertFirst("f");
		Assert.assertArrayEquals(new String[] {"f", "b", "a", "c", "d", "e" }, listaStringDouble.toArray());
		Assert.assertEquals(6, listaStringDouble.size());
		Assert.assertEquals("f", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("e", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		
		listaStringDouble.removeLast();
		Assert.assertArrayEquals(new String[] { "f", "b", "a", "c", "d" }, listaStringDouble.toArray());
		Assert.assertEquals(5, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertNull(listaStringDouble.search("e"));
		
		listaStringDouble.removeLast();
		Assert.assertArrayEquals(new String[] { "f", "b", "a", "c"}, listaStringDouble.toArray());
		Assert.assertEquals(4, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertNull(listaStringDouble.search("d"));
		Assert.assertEquals("f", listaStringDouble.search("f"));
		Assert.assertEquals("b", listaStringDouble.search("b"));
		Assert.assertEquals("c", listaStringDouble.search("c"));
		Assert.assertEquals("a", listaStringDouble.search("a"));
		
		listaStringDouble.removeLast();
		Assert.assertArrayEquals(new String[] { "f", "b", "a", }, listaStringDouble.toArray());
		Assert.assertEquals(3, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertNull(listaStringDouble.search("c"));
		Assert.assertEquals("f", listaStringDouble.search("f"));
		Assert.assertEquals("b", listaStringDouble.search("b"));
		Assert.assertEquals("a", listaStringDouble.search("a"));
		
		listaStringDouble.removeLast();
		Assert.assertArrayEquals(new String[] { "f", "b" }, listaStringDouble.toArray());
		Assert.assertEquals(2, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertEquals("f", listaStringDouble.search("f"));
		Assert.assertEquals("b", listaStringDouble.search("b"));
		Assert.assertNull(listaStringDouble.search("a"));
		Assert.assertEquals("f", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("b", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeLast();
		Assert.assertArrayEquals(new String[] { "f" }, listaStringDouble.toArray());
		Assert.assertEquals(1, listaStringDouble.size());
		Assert.assertFalse(listaStringDouble.isEmpty());
		Assert.assertEquals("f", listaStringDouble.search("f"));
		Assert.assertEquals("f", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("f", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeLast();
		Assert.assertArrayEquals(new String[] {  }, listaStringDouble.toArray());
		Assert.assertEquals(0, listaStringDouble.size());
		Assert.assertTrue(listaStringDouble.isEmpty());
		Assert.assertNull(listaStringDouble.search("f"));
		Assert.assertEquals("NIL", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("NIL", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insertFirst(null);
		listaStringDouble.insertFirst(null);
		listaStringDouble.insert(null);
		listaStringDouble.insert(null);
		listaStringDouble.insert(null);
		listaStringDouble.insertFirst(null);
		Assert.assertEquals(0, listaStringDouble.size());
		Assert.assertTrue(listaStringDouble.isEmpty());
		Assert.assertEquals("NIL", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("NIL", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.removeLast();
		listaStringDouble.remove("s");
		listaStringDouble.removeFirst();
		Assert.assertEquals(0, listaStringDouble.size());
		Assert.assertTrue(listaStringDouble.isEmpty());
		Assert.assertEquals("NIL", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("NIL", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insertFirst("Z");		
		listaStringDouble.insert("z");
		Assert.assertArrayEquals(new String[] {"Z", "z"  }, listaStringDouble.toArray());
		
		listaStringDouble.insert("Z");
		listaStringDouble.insertFirst("z");
		Assert.assertArrayEquals(new String[] {"z", "Z", "z", "Z"}, listaStringDouble.toArray());
		
		Assert.assertEquals("z", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("Z", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		listaStringDouble.insert("z");
		listaStringDouble.insertFirst("Z");
		Assert.assertArrayEquals(new String[] {"Z", "z", "Z", "z", "Z", "z" }, listaStringDouble.toArray());
		
		listaStringDouble.remove("Z");
		Assert.assertArrayEquals(new String[] {"z", "Z", "z", "Z", "z" }, listaStringDouble.toArray());
		
		listaStringDouble.remove("Z");
		Assert.assertArrayEquals(new String[] {"z", "z", "Z", "z" }, listaStringDouble.toArray());
		
		listaStringDouble.remove("Z");
		Assert.assertArrayEquals(new String[] {"z", "z", "z" }, listaStringDouble.toArray());
		
		listaStringDouble.remove("z");
		Assert.assertArrayEquals(new String[] {"z", "z"}, listaStringDouble.toArray());
		
		listaStringDouble.remove("z");
		Assert.assertArrayEquals(new String[] {"z"}, listaStringDouble.toArray());
		Assert.assertEquals("z", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("z", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
		
		
		listaStringDouble.insert("h");
		Assert.assertArrayEquals(new String[] {"z", "h"}, listaStringDouble.toArray());
		
		listaStringDouble.insertFirst("a");
		listaStringDouble.insertFirst("b");
		Assert.assertArrayEquals(new String[] {"b", "a", "z", "h"}, listaStringDouble.toArray());
		
		listaStringDouble.insert("l");
		listaStringDouble.insert("m");
		Assert.assertArrayEquals(new String[] {"b", "a", "z", "h", "l", "m"}, listaStringDouble.toArray());
		
		Assert.assertEquals("b", ((SingleLinkedListImpl<String>) listaStringDouble).getHead().toString());
		Assert.assertEquals("m", ((DoubleLinkedListImpl<String>) listaStringDouble).getLast().toString());
	}

}