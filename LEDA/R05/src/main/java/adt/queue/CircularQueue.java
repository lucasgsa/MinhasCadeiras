package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) throw new QueueOverflowException();
		if (element == null) return;
		if (isEmpty()) {
			this.head = 0;
			this.tail = 0;
			this.array[tail] = element;
			elements++;
		}
		else {
			this.tail = (this.tail+1) % array.length;
			this.array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) throw new QueueUnderflowException();
		T result = array[head];
		if (elements == 1) {
			this.head = -1;
			this.tail = -1;
		}
		else {
			this.head = ((this.head + 1) % array.length);
		}
		elements--;
		return result;
	}

	@Override
	public T head() {
		if (isEmpty()) return null;
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		if (array.length == 0) return true;
		return elements == array.length;
	}

}
