package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T extends Comparable> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super();
		last = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insert(T element) {
		// Verifica se o elemento de entrada � nulo.
		if (element == null) return;
		// Verifica se a linkedlist � vazia, se for precisa criar do come�o.
		if (isEmpty()) { 
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
			head = node;
			last = (DoubleLinkedListNode<T>) node;
			size++;
		}
		// Se n�o for vazia, cria um novo n� e coloca no final.
		else {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), last);
			last.setNext(node);
			last = node;
			size++;
		}
	}
	
	public void func() {
		DoubleLinkedListNode<T>  lastStd = (DoubleLinkedListNode<T>) this.head;
		DoubleLinkedListNode<T> nextNode = new DoubleLinkedListNode<T>();
		DoubleLinkedListNode<T> currentNode = null;
		T temp = null;
		if (!lastStd.isNIL()) {
			nextNode = (DoubleLinkedListNode<T>) lastStd.next;
		}
		while (nextNode != null && !nextNode.isNIL()) {
			currentNode = lastStd;
			temp = nextNode.getData();
			while (!currentNode.isNIL() && (temp.compareTo(currentNode.getData()) < 0)) {
				System.out.println(Arrays.toString(toArray()));
				nextNode.data = currentNode.getData();
				currentNode = currentNode.previous;
				nextNode = (DoubleLinkedListNode<T>) currentNode.next;
			}
			if (nextNode != null) nextNode.data = temp;
			lastStd = (DoubleLinkedListNode<T>) lastStd.next;
			nextNode = (DoubleLinkedListNode<T>) lastStd.next;
		}
	}
	
	@Override
	public void remove(T element) {
		// Se j� for vazio n�o faz nada.
		if (isEmpty()) return;
		if (element == null) return;
		DoubleLinkedListNode<T> atual = (DoubleLinkedListNode<T>) getHead();
		while (!atual.isNIL()) {
			if (atual.getData().equals(element)) {
				if (atual.getNext().isNIL()) {
					last = atual.previous;
				}
				if (atual.getPrevious().isNIL()) {
					head = atual.next;
				}
				atual.getPrevious().setNext(atual.getNext());
				((DoubleLinkedListNode<T>) atual.getNext()).setPrevious(atual.getPrevious());
				size--;
				return;
			}
			atual = (DoubleLinkedListNode<T>) atual.next;
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if (element == null) return;
		if (isEmpty()) { 
			this.head = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
			last = (DoubleLinkedListNode<T>) head;
			size++;
		}
		else {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>)head, new DoubleLinkedListNode<>());
			((DoubleLinkedListNode<T>)head).setPrevious(node);
			head = node;
			size++;
		}
	}

	@Override
	public void removeFirst() {
		if (isEmpty()) return;
		if (head.next.isNIL()) {
			head = new DoubleLinkedListNode<>();
			last = (DoubleLinkedListNode<T>)head;
			size--;
		}
		else {
			head = head.getNext();
			((DoubleLinkedListNode<T>)head).setPrevious(new DoubleLinkedListNode<>());
			size--;
		}
	}

	@Override
	public void removeLast() {
		if (isEmpty()) return;
		if (last.previous.isNIL()) {
			head = new DoubleLinkedListNode<>();
			last = (DoubleLinkedListNode<T>)head;
			size--;
		}
		else {
			last = last.getPrevious();
			last.setNext(new DoubleLinkedListNode<>());
			size--;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
