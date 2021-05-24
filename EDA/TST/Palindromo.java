class Palindromo {
	public static boolean eh_palindromo(char[] palavra) {
		int tamanho = palavra.length;
		if (tamanho == 1) {
			return true;
		}
		for (int i = 0; i < tamanho/2; i++) {
			if (palavra[i] != palavra[tamanho-1-i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		char[] array = {'a', 'n', 'a', 'n', 'a'};
		System.out.println(eh_palindromo(array));
	}
}
