import java.util.Scanner;

class BuscaBinariaRecursiva {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] itens = converteArray(sc.nextLine().split(" "));
		int valor = sc.nextInt();
		System.out.println(buscar(itens, valor));
		sc.close();
	}
	
	public static int buscar(int[] array, int valor) {
		return busca(array, 0, array.length-1, valor);
	}
	
	private static int busca(int[] array, int inicio, int fim, int valor) {
		int i = (inicio+fim)/2;
		if (array[i] == valor) return i;
		System.out.println(i);
		if (inicio == fim) return -1;
		if (array[i] < valor) return busca(array, i+1, fim, valor);
		else return busca(array, inicio, i-1, valor);
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
