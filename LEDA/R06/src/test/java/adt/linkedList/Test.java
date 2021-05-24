package adt.linkedList;

import static org.junit.Assert.*;

import java.util.Arrays;

public class Test {

	@org.junit.Test
	public void test() {
		DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
		lista.insert(5);
		lista.insert(4);
		lista.insert(3);
		lista.insert(2);
		lista.insert(1);
		lista.func();
		System.out.println(Arrays.toString(lista.toArray()));
	}

}
