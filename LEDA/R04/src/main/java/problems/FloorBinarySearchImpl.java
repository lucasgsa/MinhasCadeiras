 package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		ordenar(array, 0, array.length-1);
		return binareFloor(array, x, 0, array.length-1);
	}
	
	private Integer binareFloor(Integer[] array, Integer x, int left, int right) {
		if (right < left) return null;
		int meio = (left+right)/2;
		
		if (array[meio].equals(x)) {
			return array[meio];
		}
		
		if (array[meio] < x) {
			if (meio == right) {
				return array[meio];
			}
			else if (array[meio+1] > x ) {
				return array[meio];
			}
			else {
				return binareFloor(array, x, meio+1, right);
			}
		}
		
		if (array[meio] > x) {
			if (meio == left) {
				return null;
			}
			if (array[meio-1] < x) {
				return array[meio-1];
			}
			else {
				return binareFloor(array, x, left, meio-1);
			}
		}
		return null;
	}
	
	private void ordenar(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0 | rightIndex >= array.length) return;
		if (leftIndex >= rightIndex) return;
		if (array.length == 0) return;
		
		mediana(array, leftIndex, rightIndex);
		util.Util.swap(array, leftIndex, (leftIndex+rightIndex)/2);
		
		Integer pivot = array[leftIndex];
		int i = leftIndex;
		for (int j = i+1; j<=rightIndex; j++) {
			if (pivot.compareTo(array[j]) > 0) {
				i++;
				util.Util.swap(array, i, j);
			}
		}
		util.Util.swap(array, i, leftIndex);
		
		ordenar(array, leftIndex, i);
		ordenar(array, i+1, rightIndex);
	}
	
	/**
	 * Ordena o valor inicial, do meio e do final do array de forma crescente.
	 * @param array
	 * @param ini
	 * @param fim
	 */
	private void mediana(Integer[] array, int ini, int fim) {
		int meio = (ini+fim)/2;
		if (array[fim].compareTo(array[meio]) < 0) util.Util.swap(array, fim, meio);
		if (array[meio].compareTo(array[ini]) < 0) util.Util.swap(array, meio, ini);
		if (array[fim].compareTo(array[meio]) < 0) util.Util.swap(array, fim, meio);
	}
}
