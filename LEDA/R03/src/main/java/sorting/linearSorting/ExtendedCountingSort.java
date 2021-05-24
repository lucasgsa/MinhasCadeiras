package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {
	
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		// Checagens iniciais
		if (!checkValidArguments(array, leftIndex, rightIndex)) return;
		
		// Descobrindo os indices do maior e menor valor do array.
		int[] minAndMax = getMinAndMax(array, leftIndex, rightIndex);
		int min = minAndMax[0];
		int max = minAndMax[1];
		
		// Deslocamento do array a fim de minimizar o desperd�cio de mem�ria, deixando sempre o menor valor na posi��o 0 do array de frequ�ncia.
		int shift = array[min]*-1;
		
		// Tamanho do array de frequ�ncia.
		int size = array[max]-array[min]+1;
		
		// Gera o array de frequ�ncias.
		int[] C = generateFrequency(array, leftIndex, rightIndex, size, shift);
		
		// Faz a cumulativa do array de frequ�ncias.
		cumulativa(C);
		
		// Gera um novo array ordenado a partir do array de frequ�ncias.
		Integer[] B = getArrayByFrequency(C, leftIndex, rightIndex, shift, array);
		
		// E por fim, insere no array principal a sequ�ncia j� ordenada, passando para o array original o array B.
		insertArrayInArray(array, B, leftIndex);
	}
	
	/**
	 * Checa se os argumentos passados para ordena��o s�o v�lidos ou se n�o precisam de ordena��o por conter 1 ou menos elementos.
	 * @param array, o array a ser ordenado.
	 * @param leftIndex, inicio do intervalo a ser ordenado.
	 * @param rightIndex, fim do intervalo a ser ordenado.
	 * @return boolean, retorna true para caso os argumentos forem v�lidos, e false caso n�o forem ou n�o precisem de ordena��o.
	 */
	private static boolean checkValidArguments(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return false;
		if(rightIndex <= leftIndex) return false;
		
		if (rightIndex >= array.length) return false;
		if (leftIndex < 0) return false;
		return true;
	}
	
	/**
	 * Retorna um array de tamanho 2, contendo o par indice do menor valor e indice do maior valor respectivamente 
	 * no intervalo do array de init at� end.
	 * @param array, recebe o array de Integer.
	 * @param init(incluso), inicio do intervalo para busca.
	 * @param end(incluso), fim do intervalo para busca.
	 * @return int[], retorna um array de tamanho 2, com array[0] sendo o indice do menor elemento e array[1] sendo o maior.
	 */
	private static int[] getMinAndMax(Integer[] array, int init, int end){
		int max = init;
		int min = init;
		for (int i = init; i <= end; i++) {
			if (array[i] > array[max]) max = i;
			if (array[i] < array[min]) min = i;
		}
		int[] pair = {min, max};
		return pair;
	}
	
	/**
	 * Gera as frequ�ncias de valores de um indice a outro do array original, recebendo o tamanho que deseja para o array de contagem
	 * e o deslocamento desejado que tenha dentro do array de contagem a fim de minimizar o uso de mem�ria sem necessidade.
	 * @param originalArray, recebe o array original n�o ordenado.
	 * @param leftIndex, recebe o indice de onde come�a a contagem.
	 * @param rightIndex, recebe o indice de onde termina a contagem.
	 * @param size, recebe o tamanho do array de contagem.
	 * @param shift, recebe o deslocamento que o array de contagem sofre para a direita ou esquerda.
	 * @return int[], retorna um array com as frequ�ncias geradas do array.
	 */
	private static int[] generateFrequency(Integer[] originalArray, int leftIndex, int rightIndex, int size, int shift) {
		int[] C = new int[size];
		for (int i = leftIndex; i <= rightIndex; i++) {
			C[originalArray[i]+shift] += 1;
		}
		return C;
	}
	
	/**
	 * Faz a cumulativa do array de inteiro adicionando a cada valor o seu anterior.
	 * Faz com que o array tenha seus valores em C[i] = C[i]+C[i-1].
	 * @param C, recebe o array de inteiros de frequ�ncia ao qual deseja fazer a cumulativa.
	 */
	private static void cumulativa(int[] C){
		for (int i = 1; i < C.length; i++) {
            C[i] += C[i-1];
        }
	}
	
	/**
	 * A partir de um array de frequ�ncia j� acumulada, gera um array final j� ordenado.
	 * @param originalArray, recebe o array original n�o ordenado.
	 * @param C, recebe o array de cumulativas.
	 * @param leftIndex, recebe o indice de onde come�a a ordena��o.
	 * @param rightIndex, recebe o indice de onde termina a ordena��o.
	 * @param shift, recebe o deslocamento que o array de contagem sofre para a direita ou esquerda a fim de manter o menor sempre no indice 0.
	 * @return Integer[], retorna a parte ordenada do array.
	 */
	private static Integer[] getArrayByFrequency(int[] C, int leftIndex, int rightIndex, int shift, Integer[] originalArray) {
		Integer[] B = new Integer[rightIndex-leftIndex+1];
		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[originalArray[i]+shift]-1] = originalArray[i];
			C[originalArray[i]+shift] -= 1;
		}
		return B;
	}
	
	/**
	 * Insere um array em outro, assim, a partir de um indice(incluso), todos os elementos ser�o adicionados ao primeiro array at� que o segundo chegue ao final.
	 * @param array1, Recebe o array em que v�o ser inseridos os valores.
	 * @param array2, Recebe o array ao qual est�o os valores a serem inseridos.
	 * @param initIndex, Recebe um inteiro que define em que indice come�ar�o a ser substituidos os valores.
	 */
	private static void insertArrayInArray(Integer[] array1, Integer[] array2, int initIndex) {
		for (int i = 0; i < array2.length; i++) {
			array1[initIndex+i] = array2[i]; 
		}
	}

}
