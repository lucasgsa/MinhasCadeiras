import java.util.Scanner;

class ElementosDuplicados {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] itens = sc.nextLine().split(" ");
		System.out.println(verificaRepeticao(itens));
		sc.close();
	}

	private static boolean verificaRepeticao(String[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (i==j) continue;
				if (array[i].equals(array[j])) {
					return true;
				}
			}
		}
		return false;
	}

}