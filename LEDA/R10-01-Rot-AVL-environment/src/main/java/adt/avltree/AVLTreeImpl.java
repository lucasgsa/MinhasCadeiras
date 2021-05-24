package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

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
            rebalanceUp(removido);

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
   rebalanceUp(removido);
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
   rebalanceUp(removido);
}

//No c 2 filhos
        else {
            BSTNode<T> sucessor = sucessor(removido.getData());
            removido.setData(sucessor.getData());
            remove(sucessor.getData());

        }

    }

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) return (height(node.getLeft()) - height(node.getRight()));
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
			rightRotate(node);
			return true;
		}
		return false;
	}
	
	protected boolean checkRR(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getRight().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getRight());
		if (balance < -1 && balanceChildren <= 0) {
			leftRotate(node);
			return true;
		}
		return false;
	}
	
	protected boolean checkLR(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getLeft().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getLeft());
		if (balance > 1 && balanceChildren < 0) {
			leftRotate((BSTNode<T>) node.getLeft());
			rightRotate(node);
			return true;
		}
		return false;
	}
	
	protected boolean checkRL(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (node.getRight().isEmpty()) return false;
		int balanceChildren = calculateBalance((BSTNode<T>) node.getRight());
		if (balance < -1 && balanceChildren > 0) {
			rightRotate((BSTNode<T>) node.getRight());
			leftRotate(node);
			return true;
		}
		return false;
	}
	
	private void leftRotate(BSTNode<T> node) {
		BSTNode<T> filhoDireita = (BSTNode<T>) node.getRight();
		BSTNode<T> neto = (BSTNode<T>) filhoDireita.getLeft();
		if (node.getParent().getRight() == node) node.getParent().setRight(filhoDireita);
		else node.getParent().setLeft(filhoDireita);
		filhoDireita.setParent(node.getParent());
		node.setParent(filhoDireita);
		filhoDireita.setLeft(node);
		node.setRight(neto);
		neto.setParent(node);
		if (root == node) root = filhoDireita;
	}
	
	private void rightRotate(BSTNode<T> node) {
		BSTNode<T> filhoEsquerda = (BSTNode<T>) node.getLeft();
		BSTNode<T> neto = (BSTNode<T>) filhoEsquerda.getRight();
		if (node.getParent().getRight() == node) node.getParent().setRight(filhoEsquerda);
		else node.getParent().setLeft(filhoEsquerda);
		filhoEsquerda.setParent(node.getParent());
		node.setParent(filhoEsquerda);
		filhoEsquerda.setRight(node);
		node.setLeft(neto);
		neto.setParent(node);
		if (root == node) root = filhoEsquerda;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null || node.isEmpty() || isEmpty()) return;
		rebalance(node);
		if (root == node) return;
		rebalanceUp((BSTNode<T>) node.getParent());
	}
}