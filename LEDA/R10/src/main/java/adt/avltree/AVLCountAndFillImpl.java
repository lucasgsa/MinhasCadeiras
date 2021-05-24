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
			if (checkRR(node)) {
				RRcounter++;
				return;
			}
			if (checkLR(node)) {
				LRcounter++;
				return;
			}
			if (checkRL(node)) {
				RLcounter++;
				return;
			}
		}
	}
	
	@Override
	public void fillWithoutRebalance(T[] arrayArg) {
		T[] array = getFullArray(arrayArg);
		this.root = null;
		Arrays.sort(array);
		ArrayList<Pair> queue = new ArrayList<Pair>();
		if (array.length == 0) return;
		queue.add(new Pair(0, array.length-1));
		while (queue.size() != 0) {
			Pair par = queue.remove(0);
			if (par.isInvalid()) continue;
			int meio = par.getMeio();
			int init = par.getInit();
			int end = par.getEnd();
			insert(array[meio]);
			if (!par.isFinal()) {
				queue.add(new Pair(init, meio-1));
				queue.add(new Pair(meio+1, end));
			}
		}
	}
	
	public T[] getFullArray(T[] array) {
		ArrayList<T> list = new ArrayList<>();
		for (T element:preOrder()) {
			if (!list.contains(element)) list.add(element);
		}
		for (T element:array) {
			if (!list.contains(element)) list.add(element);
		}
		T[] finalArray = Arrays.copyOf(array, list.size());
		for (int i = 0; i < list.size(); i++) {
			finalArray[i] = list.get(i);
		}
		return finalArray;
	}


}

class Pair {
	private Integer init;
	private Integer end;
	public Pair(Integer init, Integer end) {
		this.init = init;
		this.end = end;
	}
	public Integer getMeio() {
		return (init+end)/2;
	}
	public Integer getInit() {
		return init;
	}
	public Integer getEnd() {
		return end;
	}
	public boolean isInvalid() {
		return init > end;
	}
	public boolean isFinal() {
		return init == end;
	}
}
