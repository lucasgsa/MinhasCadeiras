package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	
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
			rebalance((BSTNode<T>) node);
		}
	}
	
	@Override
	protected void remove(BTNode<T> node) {
		if (node.isEmpty()) return;
		if (node.isLeaf()) {
			removeLeaf(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
		else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			removeOnlyRightChild(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
		else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			removeOnlyLeftChild(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
		else {
			BTNode<T> sucessor = sucessor(node);
			node.setData(sucessor.getData());
			remove(sucessor);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) return height(node.getLeft()) - height(node.getRight());
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node == null || node.isEmpty()) return;
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (checkLL(node)) return;
			if (checkRR(node)) return;
			if (checkLR(node)) return;
			if (checkRL(node)) return;
		}
	}
	
	protected boolean checkLL(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getLeft().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getLeft());
		if (balance > 1 && balanceChildren >= 0) {
			BSTNode<T> retorno = Util.rightRotation(node);
			if (retorno.getParent().isEmpty()) this.root = retorno;
			return true;
		}
		return false;
	}
	
	protected boolean checkRR(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getRight().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getRight());
		if (balance < -1 && balanceChildren <= 0) {
			BSTNode<T> retorno = Util.leftRotation(node);
			if (retorno.getParent().isEmpty()) this.root = retorno;
			return true;
		}
		return false;
	}
	
	protected boolean checkLR(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getLeft().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getLeft());
		if (balance > 1 && balanceChildren < 0) {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			BSTNode<T> retorno = Util.rightRotation(node);
			if (retorno.getParent().isEmpty()) this.root = retorno;
			return true;
		}
		return false;
	}
	
	protected boolean checkRL(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getRight().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getRight());
		if (balance < -1 && balanceChildren > 0) {
			Util.rightRotation((BSTNode<T>) node.getRight());
			BSTNode<T> retorno = Util.leftRotation(node);
			if (retorno.getParent().isEmpty()) this.root = retorno;
			return true;
		}
		return false;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null || node.isEmpty() || isEmpty()) return;
		rebalance(node);
		if (root == node) return;
		rebalanceUp((BSTNode<T>) node.getParent());
	}
}
