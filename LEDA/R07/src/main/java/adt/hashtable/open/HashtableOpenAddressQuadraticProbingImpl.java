package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		if (elements >= capacity() && indexOf(element) == -1) throw new HashtableOverflowException();
		HashFunctionOpenAddress<T> hashFunc = ((HashFunctionOpenAddress<T>) getHashFunction());
		int probe = 0;
		int hashElement = hashFunc.hash(element, probe)%capacity();
		while (true) {
			// Se achar um lugar vazio.
			if (table[hashElement] == null || table[hashElement] == deletedElement) {
				table[hashElement] = element;
				COLLISIONS += probe;
				elements++;
				break;
			}
			// Se achar o próprio valor na lista, atualiza.
			else if (table[hashElement].equals(element)) {
				table[hashElement] = element;
				break;
			}
			// Se não, continua, agora adicionando +1 no probe.
			else hashElement = hashFunc.hash(element, ++probe)%capacity();
		}
	}

	@Override
	public void remove(T element) {
		if (element == null) return;
		int index = indexOf(element);
		if (index == -1) return;
		table[index] = deletedElement;
		elements--;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		int index = indexOf(element);
		if (index == -1) return null;
		return (T) table[index];
	}

	@Override
	public int indexOf(T element) {
		if (element == null) return -1;
		if (capacity() == 0) return -1;
		HashFunctionOpenAddress<T> hashFunc = ((HashFunctionOpenAddress<T>) getHashFunction());
		int probe = 0;
		int hashElement = hashFunc.hash(element, probe)%capacity();
		int firstHash = hashElement;
		while (true) {
			// Se tiver hash tiver retornado ao inicio sem achar ele para.
			if (probe != 0 && firstHash == hashElement) return -1;
			if (probe > capacity()) return -1;
			// Se achar um null, significa que não há mais como ter elementos desse mesmo hash na frente e portanto pode parar.
			if (table[hashElement] == null) return -1;
			// Se achar o elemento.
			else if (table[hashElement].equals(element)) return hashElement;
			// Se não achar, e não for condição de parada, continua procurando.
			else hashElement = hashFunc.hash(element, ++probe)%capacity();
		}
	}
}
