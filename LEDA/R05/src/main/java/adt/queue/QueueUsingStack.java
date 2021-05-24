package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (stack1.isFull()) throw new QueueOverflowException();
		if (element == null) return;
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (stack1.isEmpty()) throw new QueueUnderflowException();
		transferirPilha(stack1, stack2);
		T result;
		try {
			result = stack2.pop();
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}
		transferirPilha(stack2, stack1);
		return result;
	}

	
	private void transferirPilha(Stack<T> s1, Stack<T> s2) {
		while (!s1.isEmpty()) {
			try {
				s2.push(s1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T head() {
		transferirPilha(stack1, stack2);
		T result = stack2.top();
		transferirPilha(stack2, stack1);
		return result;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
