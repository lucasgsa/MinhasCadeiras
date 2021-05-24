package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}


	@Override
	public boolean isEmpty() {
		return getData() == null && getNext() == null;
	}

	@Override
	public int size() {
		if (isEmpty()) return 0;
		return 1+getNext().size();
	}

	@Override
	public T search(T element) {
		if (isEmpty()) return null;
		if (getData().equals(element)) return data;
		return getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		recursiveInsert(element);
	}
	
	private void recursiveInsert(T element) {
		if (isEmpty()) {
			setData(element);
			setNext(new RecursiveSingleLinkedListImpl<>());
		}
		else {
			getNext().recursiveInsert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (isEmpty()) return;
		if (element == null) return;
		recursiveRemove(element);
	}
	
	private void recursiveRemove(T element) {
		if (isEmpty()) return;
		if (getData().equals(element)) {
			setData(next.getData());
			setNext(next.getNext());
		}
		else {
			getNext().recursiveRemove(element);
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		
		// Se for o NIL retorna o array vazio, condição de parada.
		if (isEmpty()) return array;
		array[0] = getData();
		
		// Array dos next.
		T[] arrayRecursivo = getNext().toArray();
		// Adiciona os próximos ao array que será retornado.
		for (int i = 0; i < arrayRecursivo.length; i++) {
			array[i+1] = arrayRecursivo[i];
		}
		return array;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
