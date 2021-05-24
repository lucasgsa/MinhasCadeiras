package orderStatistic;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso redua a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if (array.length == 0) return null;
		if (k > array.length | k < 1) return null;
		return recursiveQuickSelect(array, k, 0, array.length-1);
	}
	
	private T recursiveQuickSelect(T[] array, int k, int leftIndex, int rightIndex) {
		
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
		else if (k-1 < i) return recursiveQuickSelect(array, k, leftIndex, i-1);
		else return recursiveQuickSelect(array, k, i+1, rightIndex);
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
