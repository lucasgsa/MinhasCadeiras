import java.util.Arrays;
import java.util.Scanner;

class InsereOrdenadoUltimo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] entrada = converteArray(sc.nextLine().split(" "));
		insereOrdenadoUltimo(entrada);
		System.out.println(Arrays.toString(entrada));
		sc.close();
	}
	
	public static void insereOrdenadoUltimo(int[] array) {
		for (int i = array.length-1; i > 0; i--) {
			if (array[i] < array[i-1]) {
				int aux = array[i];
				array[i] = array[i-1];
				array[i-1] = aux;
			}
			else break;
		}
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
