import java.util.Scanner;

class BuscaLinearRecursiva {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] itens = sc.nextLine().split(" ");
		String valor = sc.nextLine();
		sc.close();
		System.out.println(encontraIndice(itens, valor, 0));
	}
	
	public static int encontraIndice(String[] array, String valor, int indice) {
		if (indice >= array.length) return -1;
		if (array[indice].equals(valor)) return indice;
		return encontraIndice(array, valor, indice+1);
	}
}
