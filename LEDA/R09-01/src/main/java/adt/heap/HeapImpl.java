package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
	
	/**
	 * Valor a ser retornado caso comparator seja minimo.
	 */
	protected final String MIN = "MIN";
	
	/**
	 * Valor a ser retornado caso comparator seja máximo.
	 */
	protected final String MAX = "MAX";

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if (ehFolha(position) || !ehValido(position)) return;
		int max = maxBetween(position, left(position), right(position));
		if (max != position) {
			util.Util.swap(heap, position, max);
			heapify(max);
		}
	}
	
	private int maxBetween(int arg1, int arg2, int arg3) {
		int max = arg1;
		if (ehValido(arg2) && comparator.compare(heap[arg2], heap[max]) > 0) {
			max = arg2;
		}
		if (ehValido(arg3) && comparator.compare(heap[arg3], heap[max]) > 0) {
			max = arg3;
		}
		return max;
	}
	
	private boolean ehValido(int indiceArg) {
        return indiceArg >= 0 && indiceArg <= this.index;
    }
	
	private boolean ehFolha(int indiceArg) {
        return indiceArg > parent(this.index) && indiceArg <= this.index;
    }

	@Override
	public void insert(T element) {
		if (element == null) return;
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		index += 1;
		this.heap[index] = element;
		int i = index;
		while (i > 0 && comparator.compare(this.heap[parent(i)], this.heap[i]) < 0) {
			util.Util.swap(heap, i, parent(i));
			i = parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array == null) return;
		this.heap = Arrays.copyOf(array, array.length);
		this.index = array.length-1;
		for (int i = parent(this.index); i >= 0; i--) heapify(i);
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) return null;
		T saida = this.heap[0];
		this.heap[0] = this.heap[index];
		this.index--;
		this.heapify(0);
		return saida;
	}

	@Override
	public T rootElement() {
		if (isEmpty()) return null;
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array == null) return array;
		buildHeap(array);
		if (getType() == this.MIN) return extractToArrayMin(array);
		else return extractToArrayMax(array);
	}
	
	private T[] extractToArrayMin(T[] array) {
		for (int i = 0; i < this.heap.length; i++) {
			array[i] = this.extractRootElement();
		}
		return array;
	}
	
	private T[] extractToArrayMax(T[] array) {
		for (int i = this.heap.length-1; i >= 0; i--) {
			array[i] = this.extractRootElement();
		}
		return array;
	}
	
	/**
	 * Retorna se o tipo do heap, se é max ou min.
	 * A constante MAX e MIN já guarda o valor a ser retornado e pode ser utilizado para checar o retorno de getType em outras funções que herdem dessa classe.
	 * @return String, "MAX" or "MIN".
	 */
	protected String getType() {
		if (isEmpty() || heap[0].compareTo(heap[this.index]) < 0) {
			return this.MIN;
		}
		else {
			return this.MAX;
		}
	}

	@Override
	public int size() {
		return index+1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
