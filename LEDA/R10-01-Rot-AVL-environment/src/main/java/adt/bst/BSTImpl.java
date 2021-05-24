package adt.bst;
import java.util.LinkedList;

import adt.bt.BTNode;

   
public abstract class BSTImpl<T extends Comparable<T>> implements BST<T> {
   
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
  		if(root == null) return -1;
  		if(root.getData() == null) return -1;
  		
  		return height(this.root);
  	}
  	
  	protected int height(BTNode<T> btNode) {
  		if(btNode == null) return -1;
  		
  		return 1 + Math.max(height((BSTNode<T>) btNode.getLeft()), height((BSTNode<T>) btNode.getRight()));
  	}
  	
	@Override
  	public BSTNode<T> search(T element) {
  		BSTNode<T> aux = this.root;
  		
  		while(aux != null) {
			if(aux.getData().equals(element)) return aux;
  			
			if(element.compareTo(aux.getData()) < 0) 
  				aux = (BSTNode<T>) aux.getLeft();
 			else 
  				aux = (BSTNode<T>) aux.getRight();
  		}
  		
  		return new BSTNode<T>();
  	}
  
  	@Override
  	public void insert(T element) {
  
  		if(element == null) return;
  		
  		if(isEmpty()) {
  			root.setData(element);
  			return;
  		}
  				
  		BSTNode<T> newNode = new BSTNode<T>();
  		newNode.setData(element);
  		
  		BSTNode<T> aux = this.root;
  		
 		while(aux != null) {
 			if(element.compareTo(aux.getData()) < 0) {
  				if(aux.getLeft() == null) {
  					aux.setLeft(newNode);
  					newNode.setParent(aux);
 					return;
 				}
  
 				aux = (BSTNode<T>) aux.getLeft();
 			}
  			else {
  				if(aux.getRight() == null) {
  					aux.setRight(newNode);
  					newNode.setParent(aux);
  					return;
  				}
 
  				aux = (BSTNode<T>) aux.getRight();
  			}
  		}
  		
  	}
  
  	@Override
  	public BSTNode<T> maximum() {
  		if(isEmpty()) return null;
  		
  		BSTNode<T> aux = this.root;
  
  		while(aux != null) {
  			if(aux.getRight() == null) return aux;
 			
 			aux = (BSTNode<T>) aux.getRight();
 		}
 		
 		return null;
 	}
 
 	@Override
public BSTNode<T> minimum() {
 		if(isEmpty()) return null;
 		
		BSTNode<T> aux = this.root;
 		
		while(aux != null) {
			if(aux.getLeft() == null) return aux;
		
 			aux = (BSTNode<T>) aux.getLeft();
 		}
 		
 		return null;
 	}
 
 	@Override
 	public BSTNode<T> sucessor(T element) {
 		
 		if(isEmpty()) return new BSTNode<T>();
 
		BSTNode<T> sucessor = (BSTNode<T>) search((T) element);
 		if(sucessor.getRight() != null) {
 			sucessor = (BSTNode<T>) sucessor.getRight();
 			while(sucessor != null) {
 				if(sucessor.getLeft() == null) return sucessor;
 				
 				sucessor = (BSTNode<T>) sucessor.getLeft();
 			}
		}
 		else {
 			 BSTNode<T> aux = (BSTNode<T>) sucessor.getParent();
 	            
 		      while (aux != null && aux.getData().compareTo(sucessor.getData()) < 0)
 		          aux = (BSTNode<T>) aux.getParent();
 		           
 		      return aux;
 		}
 		
 		
 		return null;
 
 	}
 
 	@Override
 	public BSTNode<T> predecessor(T element) {
 		
 		if(isEmpty()) return new BSTNode<T>();
 
 		BSTNode<T> predecessor = (BSTNode<T>) search(element);
 		
 		if(predecessor.getLeft() != null) {
 			predecessor = (BSTNode<T>) predecessor.getLeft();
 			while(predecessor != null) {
 				if(predecessor.getRight() == null) return predecessor;
 				
 				predecessor = (BSTNode<T>) predecessor.getRight();
 			}
 		}
 		else {
 			 BSTNode<T> aux = (BSTNode<T>) predecessor.getParent();
 	            
 		      while (aux != null && aux.getData().compareTo(predecessor.getData()) > 0)
 		          aux = (BSTNode<T>) aux.getParent();
 		           
 		      return aux;
 		}
 		
 		return null;
 	}
 
 	@Override
 	public void remove(T element) {
 		BSTNode<T> removido = search(element);
 		
 		if(removido == null) return;
 
 		//No folha
 		if(removido.isLeaf()) {
 			if(removido.equals(this.root)) this.root = new BSTNode<T>();
 
 			
 			if(element.compareTo(removido.getParent().getData()) < 0)
 				removido.getParent().setLeft(null);
 
 			else 
 				removido.getParent().setRight(null);
 
 		}
 		//No c 1 filho na esquerda
else if(removido.getLeft() != null & removido.getRight() == null) {
	if(removido.equals(this.root)) {
		this.root = (BSTNode<T>) removido.getLeft();
		this.root.setParent(null);
	}
	else {
		removido.getLeft().setParent(removido.getParent());
		
		if(element.compareTo(removido.getParent().getData()) < 0) 
			removido.getParent().setLeft(removido.getLeft());
		
		else
			removido.getParent().setRight(removido.getLeft());
	}
}

//No c 1 filho na direita
else if(removido.getLeft() == null & removido.getRight() != null) {
	if(removido.equals(this.root)) {
		this.root = (BSTNode<T>) removido.getRight();
		this.root.setParent(null);
	}
	else {
		removido.getRight().setParent(removido.getParent());
		
		if(element.compareTo(removido.getParent().getData()) < 0) 
			removido.getParent().setLeft(removido.getRight());
		
		else
			removido.getParent().setRight(removido.getRight());
	}
}

//No c 2 filhos
 		else {
 			BSTNode<T> sucessor = sucessor(removido.getData());
 			removido.setData(sucessor.getData());
 			remove(sucessor.getData());
 			
 		}
 
 	}
 
 	@Override
 	public T[] preOrder() {
 		
 		LinkedList<T> lista = new LinkedList<T>();
 		
 		lista = preOrder(this.root, lista);
 		
 		T[] array = (T[]) lista.toArray();
 		
 		return array;
 	}
 	
 	private LinkedList<T> preOrder(BSTNode<T> no, LinkedList<T> lista) {
 		if(no != null) {
 			lista.add(no.getData());
 			
			preOrder((BSTNode<T>) no.getLeft(), lista);
 			preOrder((BSTNode<T>) no.getRight(), lista);
 			
			return lista;
 		}
 		
 		return null;
 	}
 
 	@Override
 	public T[] order() {
 		
		LinkedList<T> lista = new LinkedList<T>();
 		
 		lista = order(this.root, lista);
 		Object array[] = lista.toArray();
 		
 		return (T[]) array;
 	}
 	
 	private LinkedList<T> order(BSTNode<T> no, LinkedList<T> lista) {
 		if(no != null) {
 			order((BSTNode<T>) no.getLeft(), lista);
 			
 			lista.add(no.getData());
 			
 			order((BSTNode<T>) no.getRight(), lista);
 			
 			return lista;
 			
 		}
 		
 		return null;
	}
 
 	@Override
 	public T[] postOrder() {
 		// TODO Auto-generated method stub
throw new UnsupportedOperationException("Not implemented yet!");
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
 		if(node == null) return 0;
 		
 		return 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
 	}
 
 } 