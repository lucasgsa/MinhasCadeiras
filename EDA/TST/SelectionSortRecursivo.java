import java.util.Arrays;
import java.util.Scanner;

class SelectionSortRecursivo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] entrada = converteArray(sc.nextLine().split(" "));
		SelectionSort(entrada, 0, entrada.length-1);
		sc.close();
	}
	
	public static void SelectionSort(int[] array, int left, int right) {
		if (left == right) return;
		if (array.length == 0) return;
		int menor = left;
		for (int i = left; i <= right; i++) {
			if (array[menor] > array[i]) {
				menor = i;
			}
		}
		int aux = array[menor];
		array[menor] = array[left];
		array[left] = aux;
		System.out.println(Arrays.toString(array));
		SelectionSort(array, left+1, right);
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
