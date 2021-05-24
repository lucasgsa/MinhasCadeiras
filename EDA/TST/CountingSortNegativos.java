import java.util.Arrays;
import java.util.Scanner;

class CountingSortNegativos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = converteArray(sc.nextLine().split(" "));
		int max = Integer.parseInt(sc.nextLine());
		int min = Integer.parseInt(sc.nextLine());
		countingSort(array, min, max);
		sc.close();
	}
	
	public static void countingSort(int[] array, int min, int max) {
		
		int shift = min*-1;
		
		// Contagem
		int[] C = new int[max+shift+1];
		for (int value:array) {
			C[value+shift] += 1;
			System.out.println(Arrays.toString(C));
		}
		
		// Cumulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}
		System.out.println("Cumulativa do vetor de contagem - "+Arrays.toString(C));
		
		// Criando array b
		int[] B = new int[array.length];
		for (int i = array.length-1; i >= 0; i--) {
			B[C[array[i]+shift]-1] = array[i];
			C[array[i]+shift] -= 1;
		}
		System.out.println(Arrays.toString(C));
		System.out.println(Arrays.toString(B));
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
