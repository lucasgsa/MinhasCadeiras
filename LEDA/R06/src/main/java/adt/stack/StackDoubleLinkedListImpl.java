package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (list.size() == size) throw new StackOverflowException();
		if (element == null) return;
		list.insert(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (list.isEmpty()) throw new StackUnderflowException();
		T[] array = list.toArray();
		T element = array[array.length-1];
		list.removeLast();
		return element;
	}

	@Override
	public T top() {
		if (list.isEmpty()) return null;
		T[] array = list.toArray();
		T element = array[array.length-1];
		return element;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return list.size() == size;
	}

}
