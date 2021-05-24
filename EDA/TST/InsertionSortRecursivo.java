import java.util.Arrays;
import java.util.Scanner;

class InsertionSortRecursivo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] entrada = converteArray(sc.nextLine().split(" "));
		insereOrdenado(entrada, 1);
		sc.close();
	}
	
	public static void insereOrdenado(int[] array, int indice) {
		if (indice > array.length-1) return;
		for (int i = indice; i > 0; i--) {
			if (array[i] < array[i-1]) {
				int aux = array[i];
				array[i] = array[i-1];
				array[i-1] = aux;
			}
			else break;
		}
		System.out.println(Arrays.toString(array));
		insereOrdenado(array, indice+1);
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
