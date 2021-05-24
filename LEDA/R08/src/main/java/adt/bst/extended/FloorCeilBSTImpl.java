package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> bst = new BSTImpl<Integer>();
		for (Integer e:array) {
			bst.insert(e);
		}
		return floor(bst.getRoot(), numero);
	}
	
	private Integer floor(BSTNode<Integer> node, double numero) {
		if (node.isEmpty()) return null;
		if (node.getData() == numero) {
			return node.getData();
		}
		else if (node.getData() > numero) {
			return floor((BSTNode<Integer>) node.getLeft(), numero);
		}
		else {
			Integer floor = floor((BSTNode<Integer>) node.getRight(), numero);
			if (floor == null || floor > numero) return node.getData();
			else return floor;
		}
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> bst = new BSTImpl<Integer>();
		for (Integer e:array) {
			bst.insert(e);
		}
		return ceil(bst.getRoot(), numero);
	}
	
	public Integer ceil(BSTNode<Integer> node, double numero) {
		if (node.isEmpty()) return null;
		if (node.getData() == numero) {
			return node.getData();
		}
		else if (node.getData() < numero) {
			return ceil((BSTNode<Integer>) node.getRight(), numero);
		}
		else {
			Integer ceil = ceil((BSTNode<Integer>) node.getLeft(), numero);
			if (ceil == null || ceil < numero) return node.getData();
			return ceil;
		}
	}

}
