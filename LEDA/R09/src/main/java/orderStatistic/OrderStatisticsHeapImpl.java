package orderStatistic;

import java.util.PriorityQueue;import org.hamcrest.core.IsEqual;

public class OrderStatisticsHeapImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Existem diversas formas de se calcular uma estatistica de ordem. 
	 * Voce deve fazer isso usando uma heap restricoes:
	 * - nenhuma copia ou estrutura array auxiliar serah permitida, exceto o uso de
	 *   uma PriorityQueue
	 * - caso a estatistica de ordem procurada nao exista no array o metodo deve retornar null 
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	
	@Override
	public T getOrderStatistics(T[] array, int k) {
		if (k < 1) return null;
		PriorityQueue<T> heap = new PriorityQueue<T>();
		// Adiciona tudo na heap.
		for (T atual:array) {
			heap.add(atual);
		}
		
		// Remove da heap até chegar ao elemento desejado sem retira-lo ou não ter mais elementos.
		for (int i = 0; i < k-1; i++) {
			if (heap.isEmpty()) return null;
			heap.remove();
		}
		// O for anterior remove da heap até que o próximo a ser removido seja o desejado.
		// Verifica se ainda tem elementos.
		if (heap.isEmpty()) return null;
		// Retorna o elemento.
		return heap.remove();
	}

	
	
}
