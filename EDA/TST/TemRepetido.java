
public class TemRepetido {
	public static boolean tem_repetido(int[] v) {
		for (int i = 0; i < v.length; i++) {
			for (int j = i-1; j >= 0; j--) {
				if (v[i] == v[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] v = {1, 13, 3, 4, 5, };
		System.out.println(tem_repetido(v));
	}
}
