package adt.hashtable.closed;

import java.util.Arrays;
import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size (or the given size, if it is already prime). 
	 * For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		
		HashFunction function = HashFunctionFactory.createHashFunction(method,
				realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number.
	 * If the given number is prime, it is returned. 
	 * You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		if(util.Util.isPrime(number)) return number;
		return getPrimeAbove(number+1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element == null) return; 
		if (capacity() == 0) return;
		int hashElement = ((HashFunctionClosedAddress<T>) getHashFunction()).hash(element);
		if (table[hashElement] == null) {
			table[hashElement] = new LinkedList<T>();
			((LinkedList<T>) table[hashElement]).add(element);
			elements++; 
		}
		else {
			int index = ((LinkedList<T>) table[hashElement]).indexOf(element);
			if (index != -1){
				((LinkedList<T>) table[hashElement]).set(index, element);
			}
			else {
				((LinkedList<T>) table[hashElement]).add(element);
				COLLISIONS++;
				elements++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		if (capacity() == 0) return;
		int indexList = indexOf(element);
		if (indexList == -1) return;
		
		int indexOf = ((LinkedList<T>) table[indexList]).indexOf(element);
		if (indexOf != -1) {
			((LinkedList<T>) table[indexList]).remove(indexOf);
			elements--;
		}
	}

	@Override
	public T search(T element) {
		if (indexOf(element) != -1) return element;
		else return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		if (capacity() == 0) return -1;
		int hashElement = ((HashFunctionClosedAddress<T>) getHashFunction()).hash(element);
		Object line = table[hashElement];
		if (line == null) return -1;
		else {
			if (((LinkedList<T>) line).contains(element)) return hashElement;
		}
		return -1;
	}

}
