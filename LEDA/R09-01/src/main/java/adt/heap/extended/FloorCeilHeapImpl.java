package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}
	
	private String comparatorType() {
		if (index < 0 || comparator.compare(1, 2) > 0) return MIN;
		else return MAX;
	}
	
	private void construirHeap(Integer[] array) {
		this.index = -1;
		this.heap = new Integer[array.length];
		for (Integer element: array) {
			this.insert(element);
		}
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		if (java.util.Objects.isNull(numero) || array == null) return null;
		construirHeap(array);
		String type = comparatorType();
		if (type == this.MAX) {
			return floorMax(numero);
		}
		else {
			return floorMin(numero);
		}
	}
	
	private Integer floorMax(double numero) {
		// Se for vazio não existe floor.
		if (isEmpty()) return null;
		// Se o atual for o próprio número procurado retorna ele.
		if (rootElement() == numero) return rootElement();
		// Se a raiz da árvore for menor que o número, ele retorna a raiz, já que é o primeiro que achar menor que o número procurado.
		else if (rootElement() < numero) return rootElement();
		// Se não achar, continua procurando.
		else {
			extractRootElement();
			return floorMax(numero);
		}
	}
	
	private Integer floorMin(double numero) {
		// Se for vazio retorna vazio.
		if (isEmpty()) return null;
		Integer atual = extractRootElement();
		// Se o atual for o próprio número procurado retorna ele.
		if (atual == numero) return atual;
		// Se o atual for maior que o número significa que não há número menor que ele.
		if (atual > numero) return null;
		// Se após tirar o primeiro elemento ele fica vazio significa que só tem um elemento.
		if (isEmpty()) {
			// Se o elemento for menor ele é o floor.
			if (atual < numero) return atual;
			// Se não for, significa que não existe floor para o número.
			else return null;
		}
		// Pega o próximo elemento do heap sem remover.
		Integer proximo = rootElement();
		// Se ele for maior que o número procurado significa que o floor é o atual.
		if (proximo > numero) return atual;
		// Se não for, continua procurando o floor, agora do próximo elemento, já que o atual já foi retirado no começo.
		else return floorMin(numero);
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		if (java.util.Objects.isNull(numero) || array == null) return null;
		construirHeap(array);
		String type = comparatorType();
		if (type == this.MAX) {
			return ceilMax(numero);
		}
		else {
			return ceilMin(numero);
		}
	}
	
	private Integer ceilMax(double numero) {
		// Se for vazio não existe ceil.
		if (isEmpty()) return null;
		Integer atual = extractRootElement();
		// Se o atual for o próprio número procurado retorna ele.
		if (atual == numero) return atual;
		// Se o atual for menor que o número significa que não há número maior que ele.
		if (atual < numero) return null;
		// Se após tirar o primeiro elemento ele fica vazio significa que só tem um elemento.
		if (isEmpty()) {
			if (atual > numero) return atual;
			else return null;
		}
		// Pega o próximo elemento do heap sem remover.
		Integer proximo = rootElement();
		// Se ele for menor que o número procurado significa que o ceil é o atual.
		if (proximo < numero) return atual;
		// Se não for, continua procurando o ceil, agora do próximo elemento, já que o atual já foi retirado no começo.
		else return ceilMax(numero);
	}
	
	private Integer ceilMin(double numero) {
		// Se for vazio não existe ceil.
		if (isEmpty()) return null;
		// Se achar um igual retorna.
		if (rootElement() == numero) return rootElement();
		// Se a raiz da árvore for maior que o número, ele retorna a raiz, já que é o primeiro que achar maior que o número procurado.
		else if (rootElement() > numero) return rootElement();
		// Se não achar, continua procurando.
		else {
			extractRootElement();
			return ceilMin(numero);
		}
	}

}
