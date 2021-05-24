import java.util.Scanner;

class WordCloud {
	static int prime = 20357;
	static Integer[] array = new Integer[prime];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] palavras = sc.nextLine().split(" ");
		for (String p:palavras) {
			int hash = Math.abs(p.hashCode()%prime);
			if (array[hash] == null) array[hash] = 1;
			else array[hash] += 1;
		}
		while (true){
			String valor = sc.nextLine();
			if (valor.equals("fim")) break;
			int hash = Math.abs(valor.hashCode()%prime);
			System.out.println(array[hash]);
		}
	}
}
