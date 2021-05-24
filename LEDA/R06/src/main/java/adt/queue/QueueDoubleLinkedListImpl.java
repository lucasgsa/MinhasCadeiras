package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (list.size() == size) throw new QueueOverflowException();
		if (element == null) return;
		list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (list.isEmpty()) throw new QueueUnderflowException();
		T element = list.toArray()[0];
		list.removeFirst();
		return element;
	}

	@Override
	public T head() {
		if (list.isEmpty()) return null;
		return list.toArray()[0];
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (list.size() == size);
	}

}
