package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> filhoDireita = (BSTNode<T>) node.getRight();
		BSTNode<T> neto = (BSTNode<T>) filhoDireita.getLeft();
		if (node.getParent().getRight() == node) node.getParent().setRight(filhoDireita);
		else node.getParent().setLeft(filhoDireita);
		filhoDireita.setParent(node.getParent());
		node.setParent(filhoDireita);
		filhoDireita.setLeft(node);
		node.setRight(neto);
		neto.setParent(node);
		return filhoDireita;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> filhoEsquerda = (BSTNode<T>) node.getLeft();
		BSTNode<T> neto = (BSTNode<T>) filhoEsquerda.getRight();
		if (node.getParent().getRight() == node) node.getParent().setRight(filhoEsquerda);
		else node.getParent().setLeft(filhoEsquerda);
		filhoEsquerda.setParent(node.getParent());
		node.setParent(filhoEsquerda);
		filhoEsquerda.setRight(node);
		node.setLeft(neto);
		neto.setParent(node);
		return filhoEsquerda;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
