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
		if (Math.abs(avlTree.calculateBalance((BSTNode<T>) node)) > 1) return false;
		boolean left = isAVLTree((BSTNode<T>) node.getLeft());
		boolean right = isAVLTree((BSTNode<T>) node.getRight());
		return left && right;
	}

}