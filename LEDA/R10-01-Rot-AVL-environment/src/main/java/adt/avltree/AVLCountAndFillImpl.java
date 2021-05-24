package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node == null || node.isEmpty()) return;
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (checkLL(node)) {
				LLcounter++;
				return;
			}
			if (checkLR(node)) {
				LRcounter++;
				return;
			}
			if (checkRR(node)) {
				RRcounter++;
				return;
			}
			if (checkRL(node)) {
				RLcounter++;
				return;
			}
		}
	}
	
	@Override
	public void fillWithoutRebalance(T[] array) {
		Arrays.sort(array);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		if (array.length == 0) return;
		queue.add(0);
		queue.add(array.length-1);
		while (queue.size() != 0) {
			int inicio = queue.remove(0);
			int fim = queue.remove(0);
			int meio = (inicio+fim)/2;
			
			insert(array[meio]);
			
			if (inicio != fim && inicio > fim) {
				queue.add(inicio);
				queue.add(meio-1);
				queue.add(meio+1);
				queue.add(fim);
			}
		}
	}


}