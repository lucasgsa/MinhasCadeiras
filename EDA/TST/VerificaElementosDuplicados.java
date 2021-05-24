import java.util.Arrays;
import java.util.Scanner;

class VerificaElementosDuplicados {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean existeRepeticao = false;
		String[] array = new String[0];
		while (sc.hasNext()) {
			String atual = sc.next();
			if (contem(array, atual)) {
				existeRepeticao = true;
				break;
			}
			else {
				array = Arrays.copyOf(array, array.length+1);
				array[array.length-1] = atual;
			}
		}
		sc.close();
		System.out.println(existeRepeticao);
	}
	
	private static boolean contem(String[] array, String valor) {
		for (String temp:array) {
			if (temp.equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	private static void printarArray(String[] array) {
		for (String atual:array) {
			System.out.println(atual);
		}
		System.out.println("----");
	}
	
	private static String[] aumentarArray(String[] array, int qtdAumentada) {
		String[] newArrayTemp = new String[array.length+qtdAumentada];
		for (int i = 0; i < array.length-1; i++) {
			newArrayTemp[i] = array[i];
		}
		return newArrayTemp;
	}
}
