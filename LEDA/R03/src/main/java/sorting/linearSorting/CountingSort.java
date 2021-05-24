package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratÃ©gia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o mÃ¡ximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado nÃ£o chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {
	
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		// Checagens iniciais
		if (!checkValidArguments(array, leftIndex, rightIndex)) return;
		// Descobrindo máximo
		
		// Array de contagem
		int[] C = generateFrequency(array, leftIndex, rightIndex);
		
		// cumulativa
		cumulativa(C);
		
		// Gerando a parte do array já ordenada.
		Integer[] B = getArrayByFrequency(array, C, leftIndex, rightIndex);
		
		// Passando para o array principal
		insertArrayInArray(array, B, leftIndex);
	}
	
	/**
	 * Checa se os argumentos passados para ordenação são válidos ou se não precisam de ordenação por conter 1 ou menos elementos.
	 * @param array, o array a ser ordenado.
	 * @param leftIndex, inicio do intervalo a ser ordenado.
	 * @param rightIndex, fim do intervalo a ser ordenado.
	 * @return boolean, retorna true para caso os argumentos forem válidos, e false caso não forem ou não precisem de ordenação.
	 */
	private static boolean checkValidArguments(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return false;
		if(rightIndex <= leftIndex) return false;
		
		if (rightIndex >= array.length) return false;
		if (leftIndex < 0) return false;
		return true;
	}
	
	/**
	 * Retorna o indice do maior elemento contido no array no intervalo passado.
	 * no intervalo do array de init até end.
	 * @param array, recebe o array de Integer.
	 * @param init(incluso), inicio do intervalo para busca.
	 * @param end(incluso), fim do intervalo para busca.
	 * @return int, retorna o indice do maior elemento do array.
	 */
	private static int getMax(Integer[] array, int init, int end){
		int max = init;
		for (int i = init; i <= end; i++) {
			if (array[i] > array[max]) max = i;
		}
		return max;
	}
	
	/**
	 * Gera as frequências de valores de um indice a outro do array original.
	 * @param originalArray, recebe o array original não ordenado.
	 * @param leftIndex, recebe o indice de onde começa a contagem.
	 * @param rightIndex, recebe o indice de onde termina a contagem.
	 * @return int[], retorna um array com as frequências geradas do array.
	 */
	private static int[] generateFrequency(Integer[] originalArray, int leftIndex, int rightIndex) {
		int max = getMax(originalArray, leftIndex, rightIndex);
		int[] C = new int[originalArray[max]+1];
		for (int i = leftIndex; i <= rightIndex; i++) {
			C[originalArray[i]] += 1;
		}
		return C;
	}
	
	/**
	 * Faz a cumulativa do array de inteiro adicionando a cada valor o seu anterior.
	 * Faz com que o array tenha seus valores em C[i] = C[i]+C[i-1].
	 * @param C, recebe o array de inteiros de frequência ao qual deseja fazer a cumulativa.
	 */
	private static void cumulativa(int[] C){
		for (int i = 1; i < C.length; i++) {
            C[i] += C[i-1];
        }
	}
	
	/**
	 * A partir de um array de frequência já acumulada, gera um array final já ordenado.
	 * @param originalArray, recebe o array original não ordenado.
	 * @param C, recebe o array de cumulativas.
	 * @param leftIndex, recebe o indice de onde começa a ordenação.
	 * @param rightIndex, recebe o indice de onde termina a ordenação.
	 * @return Integer[], retorna a parte ordenada do array.
	 */
	private static Integer[] getArrayByFrequency(Integer[] originalArray, int[] C, int leftIndex, int rightIndex) {
		Integer[] B = new Integer[rightIndex-leftIndex+1];

		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[originalArray[i]]-1] = originalArray[i];
			C[originalArray[i]] -= 1;
		}
		return B;
	}
	
	/**
	 * Insere um array em outro, assim, a partir de um indice(incluso), todos os elementos serão adicionados ao primeiro array até que o segundo chegue ao final.
	 * @param array1, Recebe o array em que vão ser inseridos os valores.
	 * @param array2, Recebe o array ao qual estão os valores a serem inseridos.
	 * @param initIndex, Recebe um inteiro que define em que indice começarão a ser substituidos os valores.
	 */
	private static void insertArrayInArray(Integer[] array1, Integer[] array2, int initIndex) {
		for (int i = 0; i < array2.length; i++) {
			array1[initIndex+i] = array2[i]; 
		}
	}

}
