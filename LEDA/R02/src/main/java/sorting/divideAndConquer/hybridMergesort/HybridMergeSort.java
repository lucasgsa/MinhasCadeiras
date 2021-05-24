package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	public static int MERGESORT_APPLICATIONS = 0;
	public static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		if (leftIndex < 0 | rightIndex >= array.length) return;
		if (leftIndex >= rightIndex) return;
		if (array.length == 0) return;
		hybridMergeSort(array, leftIndex, rightIndex);
	}
	
	public void hybridMergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0 | rightIndex >= array.length) return;
		if (leftIndex >= rightIndex) return;
		if ((rightIndex - leftIndex) < SIZE_LIMIT) {
			INSERTIONSORT_APPLICATIONS += 1;
			insertionSort(array, leftIndex, rightIndex);
		}
		else {
			MERGESORT_APPLICATIONS += 1;
			int meio = (leftIndex+rightIndex)/2;
			hybridMergeSort(array, leftIndex, meio);
			hybridMergeSort(array, meio+1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}
	
	public void merge(T[] array, int ini, int meio, int fim) {
			
			T[] aux = array.clone();
			
			int i = ini;
			int j = meio+1;
			int k = ini;
			
			while (i <= meio & j <= fim) {
				if (aux[i].compareTo(aux[j]) <= 0) {
					array[k++] = aux[i++];
				}
				else {
					array[k++] = aux[j++];
				}
			}
			while (i <= meio) {
				array[k++] = aux[i++];
			}
			while (j <= fim) {
				array[k++] = aux[j++];
			}
		}
	
	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return;
		
		for (int i = leftIndex+1; i < rightIndex+1; i++) {
			for (int j = i; j > leftIndex; j--) {
				if (array[j].compareTo(array[j-1]) < 0) {
					util.Util.swap(array, j, j-1);
				}
				else break;
			}
		}
	}

	public static int getMERGESORT_APPLICATIONS() {
		return MERGESORT_APPLICATIONS;
	}

	public static int getINSERTIONSORT_APPLICATIONS() {
		return INSERTIONSORT_APPLICATIONS;
	}
}
