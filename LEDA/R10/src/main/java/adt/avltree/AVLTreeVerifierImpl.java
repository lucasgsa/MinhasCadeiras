package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;
import adt.bt.BTNode;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return isBST() && isAVLTree(avlTree.getRoot());
	}
	
	private boolean isAVLTree(BSTNode<T> node) {
		if (node == null || node.isEmpty()) return true;
		boolean right = true;
		boolean left = true;
		if (Math.abs(avlTree.calculateBalance((BSTNode<T>) node)) > 1) return false;
		if (!node.getLeft().isEmpty()) {
			left = isAVLTree((BSTNode<T>) node.getLeft());
		}
		if (!node.getRight().isEmpty()) {
			right = isAVLTree((BSTNode<T>) node.getRight());
		}
		return left && right;
	}

}
