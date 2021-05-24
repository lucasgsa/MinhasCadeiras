package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return;
		if (leftIndex < 0 | rightIndex > array.length-1 | rightIndex < leftIndex) {
			throw new IllegalArgumentException("Valores de leftIndex e rightIndex inválidos.");
		}
		
		boolean houveMudanca = false;
		do {
			houveMudanca = false;
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i+1]) > 0) {
					util.Util.swap(array, i, i+1);
					houveMudanca = true;
				}
			}	
		} while (houveMudanca);
	}
}
