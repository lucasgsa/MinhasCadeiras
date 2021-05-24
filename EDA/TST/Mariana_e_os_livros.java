import java.util.Arrays;
import java.util.Scanner;

class Mariana_e_os_livros {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] entrada = sc.nextLine().split(",");
		insereOrdenado(entrada, 0);
		sc.close();
	}
	
	public static void insereOrdenado(String[] array, int indice) {
		if (indice > array.length-1) return;
		for (int i = indice; i > 0; i--) {
			if (array[i].compareTo(array[i-1]) < 0) {
				String aux = array[i];
				array[i] = array[i-1];
				array[i-1] = aux;
			}
			else break;
		}
		printarString(array);
		insereOrdenado(array, indice+1);
	}
	
	public static void printarString(String[] array) {
		String saida = Arrays.toString(array);
		System.out.println(saida.substring(1, saida.length()-1));
	}
}
