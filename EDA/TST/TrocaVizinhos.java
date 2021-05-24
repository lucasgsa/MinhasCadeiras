import java.util.Arrays;

public class TrocaVizinhos {
	public static void troca_vizinhos(int[] array) {
		for (int i = 1; i < array.length; i = i+2) {
			int temp = array[i];
			array[i] = array[i-1];
			array[i-1] = temp;
		}
	}
	
	//Test
	public static void main(String[] args) {
		int[] v = {1, 13, 3, 4, 5};
		troca_vizinhos(v);
		System.out.println(Arrays.toString(v));
	}
}
