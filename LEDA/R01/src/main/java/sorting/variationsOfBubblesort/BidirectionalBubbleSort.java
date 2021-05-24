package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return;
		if (leftIndex < 0 | rightIndex > array.length-1 | rightIndex < leftIndex) {
			throw new IllegalArgumentException("Valores de leftIndex e rightIndex inválidos.");
		}
		
		while (true) {
			if (!sortDireita(array, leftIndex, rightIndex)) break;
			if (!sortEsquerda(array, leftIndex, rightIndex)) break;
		}
	}
	
	private boolean sortDireita(T[] array, int leftIndex, int rightIndex) {
		boolean houveMudanca = false;
		for (int i = leftIndex; i < rightIndex; i++) {
			if (array[i].compareTo(array[i+1]) > 0) {
				util.Util.swap(array, i, i+1);
				houveMudanca = true;
			}
		}
		return houveMudanca;
	}
	
	private boolean sortEsquerda(T[] array, int leftIndex, int rightIndex) {
		boolean houveMudanca = false;
		for (int i = rightIndex; i > leftIndex; i--) {
			if (array[i].compareTo(array[i-1]) < 0) {
				util.Util.swap(array, i, i-1);
				houveMudanca = true;
			}
		}
		return houveMudanca;
	}
}
