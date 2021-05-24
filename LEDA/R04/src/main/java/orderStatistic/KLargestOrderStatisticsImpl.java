package orderStatistic;

import java.util.ArrayList;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		if (k < 0) return null;
		T[] newArray = (T[]) new Object[k];
		ArrayList<T> abacate = new ArrayList<T>();
		
		/* Numero em que inicia os K elementos.
		 Ex: [9,8,7,6,5,4] ordem 3, o inverseKOrder será 6, que é a ordem de estatística do elemento tamanhoArray-k+1.
		 E todos os elementos maiores que 6 serão adicionados ao array newArray.
		 Para o caso de por exemplo existirem dois ou mais 6, que é o número menor da ordem, primeiro adiciono os maiores para só ai adicionar a quantidade que falta com repetidos.
		 */
		T inverseKOrder = orderStatistics(array, array.length-k+1);
		int iArray = 0;
		
		/* Se não, adiciona todos os elementos maiores que o de ordem de estatistica.
		 E em seguida adiciona os iguais. Assim evita que quando há elementos repetidos e a 
		 ordem é nele não tente adicionar o mesmo número todas as vezes que aparece
		 mesmo quando já atingiu a quantidade pedida. */
		for (int i = 0; i < array.length; i++) {
			if (k == iArray) break;
			if (inverseKOrder == null || array[i].compareTo(inverseKOrder) > 0) {
				newArray[iArray] = (T) array[i];
				iArray++;
			}
		}
		// Se for nulo significa que já todos os números serão adicionados já no primeiro for.
		if (inverseKOrder == null) return newArray;
		
		// For para adicionar números iguais ao tamanho-K.
		for (int i = 0; i < array.length; i++) {
			if (k == iArray) break;
			if (array[i].compareTo(inverseKOrder) == 0) {
				newArray[iArray] = (T) array[i];
				iArray++;
			}
		}	
		return newArray;
		//este metodo deve obrigatoriamente usar o orderStatistics abaixo.
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if (array.length == 0) return null;
		if (k > array.length | k < 1) return null;
		return QuickSelect(array, k, 0, array.length-1);
	}
	
	private T QuickSelect(T[] array, int k, int leftIndex, int rightIndex) {
		
		mediana(array, leftIndex, rightIndex);
		util.Util.swap(array, leftIndex, (leftIndex+rightIndex)/2);
		
		T pivot = array[leftIndex];
		int i = leftIndex;
		for (int j = i+1; j<=rightIndex; j++) {
			if (pivot.compareTo(array[j]) > 0) {
				i++;
				util.Util.swap(array, i, j);
			}
		}
		util.Util.swap(array, i, leftIndex);
		if (i == k-1) return array[i];
		else if (k-1 < i) return QuickSelect(array, k, leftIndex, i-1);
		else return QuickSelect(array, k, i+1, rightIndex);
	}
	
	/**
	 * Ordena o valor inicial, do meio e do final do array de forma crescente.
	 * @param array
	 * @param ini
	 * @param fim
	 */
	private void mediana(T[] array, int ini, int fim) {
		int meio = (ini+fim)/2;
		if (array[fim].compareTo(array[meio]) < 0) util.Util.swap(array, fim, meio);
		if (array[meio].compareTo(array[ini]) < 0) util.Util.swap(array, meio, ini);
		if (array[fim].compareTo(array[meio]) < 0) util.Util.swap(array, fim, meio);
	}
}
