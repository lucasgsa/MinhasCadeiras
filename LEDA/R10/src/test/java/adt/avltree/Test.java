package adt.avltree;

import static org.junit.Assert.*;

import java.util.Arrays;

public class Test {

	@org.junit.Test
	public void test() {
		AVLCountAndFillImpl<Integer> teste = new AVLCountAndFillImpl<Integer>();
		teste.insert(10);
		System.out.println(Arrays.toString(teste.preOrder()));
		System.out.println(teste.LLcount());
		System.out.println(teste.RRcount());
		
		teste.insert(9);
		System.out.println(Arrays.toString(teste.preOrder()));
		System.out.println(teste.LLcount());
		System.out.println(teste.RRcount());
		
		teste.insert(8);
		System.out.println(Arrays.toString(teste.preOrder()));
		System.out.println(teste.LLcount());
		System.out.println(teste.RRcount());
		
		teste.insert(7);
		System.out.println(Arrays.toString(teste.preOrder()));
		System.out.println(teste.LLcount());
		System.out.println(teste.RRcount());
		
		teste.insert(6);
		System.out.println(Arrays.toString(teste.preOrder()));
		System.out.println(teste.LLcount());
		System.out.println(teste.RRcount());
		
	}

}
