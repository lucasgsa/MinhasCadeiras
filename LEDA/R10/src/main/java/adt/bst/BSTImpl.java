package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}
	
	protected int height(BTNode<T> node) {
        if(node.isEmpty()) return -1;
        else return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

	@SuppressWarnings("unchecked")
	@Override
	public BSTNode<T> search(T element) {
		if (element == null) return new BSTNode.Builder<T>().build();
		return search(this.root, element);
	}
	
	protected BSTNode<T> search(BTNode<T> node, T element) {
	    if (node.isEmpty() | element.equals(node.getData())) return (BSTNode<T>) node;
	    if (element.compareTo(node.getData()) < 0) return search(node.getLeft(), element);
	    else return search(node.getRight(), element);
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		if (this.root == null) {
			this.root = new BSTNode.Builder<T>().data(element).build();
			this.root.setRight(new BSTNode.Builder<T>().parent(this.root).build());
			this.root.setLeft(new BSTNode.Builder<T>().parent(this.root).build());
			this.root.setParent(new BSTNode.Builder<T>().build());
		}
		else insert(element, this.root);
	}
	
	@SuppressWarnings("unchecked")
	protected void insert(T element, BTNode<T> node) {
		if (node.isEmpty()) {
			if (root == node) node.setParent(new BSTNode.Builder<T>().build());
			node.setData(element);
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
		}
		else {
			if (element.compareTo(node.getData()) < 0) {
				insert(element, node.getLeft());
			}
			else {
				insert(element, node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) return null;
		return maximumRecursive(this.root);
	}
	
	private BSTNode<T> maximumRecursive(BTNode<T> node){
		if (node.getRight().isEmpty()) return (BSTNode<T>) node;
		else return maximumRecursive(node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) return null;
		return minimumRecursive(this.root);
	}
	
	private BSTNode<T> minimumRecursive(BTNode<T> node){
		if (node.getLeft().isEmpty()) return (BSTNode<T>) node;
		else return minimumRecursive(node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BTNode<T> node = search(element);
		if (node == null) return null;
		return sucessor(node);
	}
	
	protected BSTNode<T> sucessor(BTNode<T> node){
		if (node.isEmpty()) return null;
        
	    if (!node.getRight().isEmpty())
	        return minimumRecursive(node.getRight());
	    else {
	        BTNode<T> aux = node.getParent();
	            
	        while (!aux.isEmpty() && aux.getData().compareTo(node.getData()) < 0)
	            aux = aux.getParent();
	           
	        if (aux.isEmpty()) return null;
	        return (BSTNode<T>) aux;
	    }
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BTNode<T> node = search(element);
		if (node == null) return null;
		return predecessor(node);
	}
	
	private BSTNode<T> predecessor(BTNode<T> node){
		if (node.isEmpty()) return null;
        
	    if (!node.getLeft().isEmpty())
	        return maximumRecursive(node.getLeft());
	    else {
	        BTNode<T> aux = node.getParent();
	            
	        while (!aux.isEmpty() && aux.getData().compareTo(node.getData()) > 0)
	            aux = aux.getParent();
	        
	        if (aux.isEmpty()) return null;
	        return (BSTNode<T>) aux;
	    }
	}

	@Override
	public void remove(T element) {
		BSTNode<T> removeNode = search(element);
		remove(removeNode);
	}
	
	protected void remove(BTNode<T> node) {
		if (node.isEmpty()) return;
		if (node.isLeaf()) {
			removeLeaf(node);
		}
		else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) removeOnlyRightChild(node);
		else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) removeOnlyLeftChild(node);
		else {
			BTNode<T> sucessor = sucessor(node);
			node.setData(sucessor.getData());
			remove(sucessor);
		}
		root.setParent(new BSTNode.Builder<T>().build());
	}
	
	@SuppressWarnings("unchecked")
	protected void removeOnlyLeftChild(BTNode<T> removeNode) {
		if (removeNode == this.root) {
			this.root = (BSTNode<T>) removeNode.getLeft();
			this.root.setParent(new BSTNode.Builder<T>().build());
		}
		else {
			removeNode.getLeft().setParent(removeNode.getParent());
			if (removeNode.getData().compareTo(removeNode.getParent().getData()) < 0) {
				removeNode.getParent().setLeft(removeNode.getLeft());
			}
			else {
				removeNode.getParent().setRight(removeNode.getLeft());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void removeOnlyRightChild(BTNode<T> removeNode) {
		if (removeNode == this.root) {
			this.root = (BSTNode<T>) removeNode.getRight();
			this.root.setParent(new BSTNode.Builder<T>().build());
		}
		else {
			removeNode.getRight().setParent(removeNode.getParent());
			if (removeNode.getData().compareTo(removeNode.getParent().getData()) < 0) {
				removeNode.getParent().setLeft(removeNode.getRight());
			}
			else {
				removeNode.getParent().setRight(removeNode.getRight());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void removeLeaf(BTNode<T> removeNode) {
		if (removeNode == this.root) {
			this.root = new BSTNode.Builder<T>().build();
		}
		else {
			if (removeNode.getData().compareTo(removeNode.getParent().getData()) < 0) {
				removeNode.getParent().setLeft(new BSTNode.Builder<T>().build());
			}
			else {
				removeNode.getParent().setRight(new BSTNode.Builder<T>().build());
			}
		}
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size()];
		preOrder(this.root, array);
		return array;
	}
	
	private void preOrder(BTNode<T> node, T[] array) {
	    if (node!= null && !node.isEmpty()) {
	        array[searchEnd(array)] = ((T) node.getData());
	        preOrder(node.getLeft(), array);
	        preOrder(node.getRight(), array);
	    }
	}

	@Override
	public T[] order() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size()];
		order(this.root, array);
		return array;
	}
	
	private void order(BTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			order(node.getLeft(), array);
			array[searchEnd(array)] = (node.getData());
			order(node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size()];
		postOrder(this.root, array);
		return array;
	}
	
	private void postOrder(BTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			postOrder(node.getLeft(), array);
			postOrder(node.getRight(), array);
			array[searchEnd(array)] = node.getData();
		}
	}
	
	/**
	 * Retorna o primeiro índice contendo null que achar.
	 * @param array
	 * @return
	 */
	private int searchEnd(T[] array) {
		int index = 0;
		while (true) {
			if (array[index] == null) return index;
			if (index == array.length-1) return -1;
			index++;
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (node != null && !node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
