package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	@Override
	public boolean isEmpty() {
		return previous == null | next == null;
	}
	
	@Override
	public void insert(T element) {
		if (element == null) return;
		recursiveInsert(element);
	}
	
	private void recursiveInsert(T element) {
		if (isEmpty()) {
			if (previous == null) {
				RecursiveDoubleLinkedListImpl<T> previousNIL = new RecursiveDoubleLinkedListImpl<T>();
				previousNIL.setNext(this);
				previous = previousNIL;
			}
			RecursiveDoubleLinkedListImpl<T> nextNIL = new RecursiveDoubleLinkedListImpl<T>();
			nextNIL.setPrevious(this);
			setNext(nextNIL);
			setData(element);
		}
		else {
			((RecursiveDoubleLinkedListImpl<T>) getNext()).recursiveInsert(element);
		}
	}
	
	@Override
	public void remove(T element) {
		if (isEmpty()) return;
		if (element == null) return;
		// Se for o primeiro elemento e único.
		if (getData().equals(element) & size() == 1) {
			data = null;
			next = null;
			previous = null;
		}
		// Se for o primeiro elemento e não único.
		else if (getData().equals(element)){
			data = next.getData();
			next = next.getNext();
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);	
		}
		else {
			recursiveRemove(element);
		}
	}
	
	private void recursiveRemove(T element) {
		if (isEmpty()) return;
		if (getData().equals(element)) {
			getPrevious().setNext(getNext());
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(getPrevious());	
		}
		else {
			getNext().remove(element);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element == null) return;
		if (isEmpty()) {
			setData(element);
			RecursiveDoubleLinkedListImpl<T> nextNIL = new RecursiveDoubleLinkedListImpl<T>();
			nextNIL.setPrevious(this);
			setNext(nextNIL);
			
			RecursiveDoubleLinkedListImpl<T> previousNIL = new RecursiveDoubleLinkedListImpl<T>();
			previousNIL.setNext(this);
			setPrevious(previousNIL);
		}
		else {
			RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
			node.setNext(next);
			node.setPrevious(this);
			node.setData(data);
			
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(node);
			setData(element);
			setNext(node);	
		}
	}

	@Override
	public void removeFirst() {
		if (isEmpty()) return;
		if (next.isEmpty()) {
			data = null;
			next = null;
			previous = null;
		}
		else {
			setData(getNext().getData());
			setNext(getNext().getNext());
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
		}
		
	}

	@Override
	public void removeLast() {
		if (isEmpty()) return;
		recursiveRemoveLast();
	}
	
	private void recursiveRemoveLast() {
		if (next.isEmpty()) {
			if (previous.isEmpty()) {
				previous = null;
			}
			data = null;
			next = null;
		}
		else {
			((RecursiveDoubleLinkedListImpl<T>) getNext()).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
