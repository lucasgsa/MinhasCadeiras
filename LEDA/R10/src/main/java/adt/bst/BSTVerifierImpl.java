package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return isBST(bst.root);
	}
	
	private boolean isBST(BTNode<T> node) {
		if (node == null || node.isEmpty()) return true;
		boolean right = true;
		boolean left = true;
		if (!node.getLeft().isEmpty()) {
			if (node.getLeft().getData().compareTo(node.getData()) > 0) return false;
			left = isBST(node.getLeft());
		}
		if (!node.getRight().isEmpty()) {
			if (node.getRight().getData().compareTo(node.getData()) < 0) return false;
			right = isBST(node.getRight());
		}
		return left && right;
	}
	
}
