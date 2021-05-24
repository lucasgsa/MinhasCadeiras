package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		buildHeap(array);
		String type = getType();
		if (type.equals("max")) {
			return floorMax(numero);
		}
		else {
			return floorMin(numero);
		}
	}
	
	private Integer floorMax(double numero) {
		if (isEmpty()) return null;
		if (rootElement() == numero) {
			return rootElement();
		}
		else if (rootElement() < numero) {
			return rootElement();
		}
		else {
			extractRootElement();
			return floorMax(numero);
		}
	}
	
	private Integer floorMin(double numero) {
		if (isEmpty()) return null;
		Integer atual = extractRootElement();
		if (isEmpty()) return atual;
		Integer proximo = rootElement();
		if (atual == numero) {
			return atual;
		}
		if (atual > numero) {
			return null;
		}
		if (proximo > numero) {
			return atual;
		}
		else {
			return floorMin(numero);
		}
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		buildHeap(array);
		String type = getType();
		if (type.equals("max")) {
			return ceilMax(numero);
		}
		else {
			return ceilMin(numero);
		}
	}
	
	private Integer ceilMax(double numero) {
		if (isEmpty()) return null;
		Integer atual = extractRootElement();
		if (isEmpty()) return atual;
		Integer proximo = rootElement();
		if (atual == numero) {
			return atual;
		}
		if (atual < numero) {
			return null;
		}
		if (proximo < numero) {
			return atual;
		}
		else {
			return ceilMax(numero);
		}
	}
	
	private Integer ceilMin(double numero) {
		if (isEmpty()) return null;
		if (rootElement() == numero) {
			return rootElement();
		}
		else if (rootElement() > numero) {
			return rootElement();
		}
		else {
			extractRootElement();
			return ceilMin(numero);
		}
	}

}
