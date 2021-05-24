package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals((BSTNode<T>) tree1.getRoot(),(BSTNode<T>) tree2.getRoot());
	}
	
	private boolean equals(BSTNode<T> n1, BSTNode<T> n2) {
		if (n1.isEmpty() != n2.isEmpty()) return false;
		if (n1.isEmpty() && n2.isEmpty()) return true;
		if (!n1.getData().equals(n2.getData())) return false;
		boolean thisEqualsLeft = equals((BSTNode<T>) n1.getLeft(),(BSTNode<T>) n2.getLeft());
		boolean thisEqualsRight = equals((BSTNode<T>) n1.getRight(),(BSTNode<T>) n2.getRight());
		return thisEqualsLeft && thisEqualsRight;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>) tree1.getRoot(),(BSTNode<T>) tree2.getRoot());
	}
	
	private boolean isSimilar(BSTNode<T> n1, BSTNode<T> n2) {
		if (n1.isEmpty() != n2.isEmpty()) return false;
		if (n1.isEmpty() || n2.isEmpty()) return true;
		boolean n1hasleft = !n1.getLeft().isEmpty();
		boolean n1hasright = !n1.getRight().isEmpty();
		boolean n2hasleft = !n2.getLeft().isEmpty();
		boolean n2hasright = !n2.getRight().isEmpty();
		if (n1hasleft != n2hasleft || n1hasright != n2hasright) {
			return false;
		}
		else {
			boolean left = isSimilar((BSTNode<T>) n1.getLeft(),(BSTNode<T>) n2.getLeft());
			boolean right = isSimilar((BSTNode<T>) n1.getRight(),(BSTNode<T>) n2.getRight());
			return left && right;
		}
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		BTNode<T> result = orderStatisticRecursive(tree, k);
		if (result == null) return null;
		else return result.getData();
	}
	
	private BTNode<T> orderStatisticRecursive(BST<T> tree, int k) {
		if (k <= 0 || tree.isEmpty()) return null;
		if (k == 1) return minimumRecursive(tree.getRoot());
		BSTNode<T> sucessor = sucessor(orderStatisticRecursive(tree, k-1));
		if (sucessor == null) return null;
		else return sucessor;
	}
	
	private BSTNode<T> sucessor(BTNode<T> node){
		if (node == null || node.isEmpty()) return null;
        
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

	private BSTNode<T> minimumRecursive(BTNode<T> node){
		if (node.getLeft().isEmpty()) return (BSTNode<T>) node;
		else return minimumRecursive(node.getLeft());
	}

}
