package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	
	protected int size;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> atual = getHead();
		while (!atual.isNIL()) {
			if (atual.getData().equals(element)) {
				return atual.getData();
			}
			atual = atual.next;
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		if (isEmpty()) { 
			this.head = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
			size++;
		}
		else {
			SingleLinkedListNode<T> atual = getHead();
			while (!atual.next.isNIL()) {
				atual = atual.next;
			}
			atual.next = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			size++;
		}
	}

	@Override
	public void remove(T element) {
		if (isEmpty()) return;
		if (element == null) return;
		SingleLinkedListNode<T> atual = getHead();
		if (atual.getData().equals(element)) {
			this.head = atual.next;
			size--;
			return;
		}
		while (!atual.next.isNIL()) {
			if (atual.next.getData().equals(element)) {
				atual.next = atual.next.next;
				size--;
				return;
			}
			atual = atual.next;
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		int index = 0;
		SingleLinkedListNode<T> atual = getHead();
		while (!atual.isNIL()) {
			array[index] = atual.getData();
			index++;
			atual = atual.next;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
