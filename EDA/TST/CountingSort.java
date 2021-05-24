import java.util.Arrays;
import java.util.Scanner;

class CountingSort {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = converteArray(sc.nextLine().split(" "));
		int max = Integer.parseInt(sc.nextLine());
		countingSort(array, max);
		sc.close();
	}
	
	public static void countingSort(int[] array, int max) {
		// Contagem
		int[] C = new int[max+1];
		for (int value:array) {
			C[value] += 1;
			System.out.println(printarString(C));
		}
		
		// Cumulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}
		System.out.println("Cumulativa do vetor de contagem - "+printarString(C));
		
		// Adicionando ao vetor B
		int[] B =  new int[array.length];
		for (int i = array.length-1; i >= 0; i--) {
			System.out.println(Arrays.toString(C));
			B[(C[array[i]]-1)] = array[i];
			C[array[i]] -= 1;
		}
		System.out.println(printarString(C));
		System.out.println(printarString(B));
	}
	
	public static String printarString(int[] array) {
		String saida = Arrays.toString(array);
		saida = saida.substring(1, saida.length()-1).replaceAll(",", "");
		return saida;
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
