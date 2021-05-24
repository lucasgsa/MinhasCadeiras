public class VerificaDivisiveis {
	public static boolean verifica_divisiveis(int[] v) {
		for (int i = 0; i < v.length; i++) {
			for (int j = i-1; j >= 0; j--) {
				if (v[i]%v[j] == 0 || v[j]%v[i] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] v = {5, 13, 3, 4, 7};
		System.out.println(verifica_divisiveis(v));
	}
}
