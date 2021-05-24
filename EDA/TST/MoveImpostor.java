import java.util.Arrays;
import java.util.Scanner;

class MoveImpostor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = converteArray(sc.nextLine().split(" "));
		colocaEmOrdem(array, encontraImpostor(array));
		System.out.println(Arrays.toString(array));
		sc.close();
	}
	
	public static int encontraImpostor(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] < array[i-1]) {
				return i;
			}
		}
		return -1;
	}
	
	public static void colocaEmOrdem(int[] array, int index) {
		for (int i = index; i > 0; i--) {
			int aux = array[i-1];
			array[i-1] = array[i];
			array[i] = aux;
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
