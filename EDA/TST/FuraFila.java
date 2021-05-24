import java.util.Arrays;
import java.util.Scanner;

class FuraFila {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] entrada = converteArray(sc.nextLine().split(" "));
		int inicial = Integer.parseInt(sc.nextLine());
		
		int index = 0;
		for (int i = inicial; i < entrada.length; i++) {
			for(int j = i; j != index; j--) {
				int temp = entrada[j];
				entrada[j] = entrada[j-1];
				entrada[j-1] = temp;
			}
			index++;
			System.out.println(Arrays.toString(entrada));
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
