package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		return result;
	}
	public long calcularFatorial(int n) {
		if (n == 0 | n == 1) return 1;
		long result = (calcularFatorial(n -1)*n);
		System.out.println(n + "! = " + result);
		return result;
	}

	public int calcularFibonacci(int n) {
		if (n == 1 | n == 2) return 1;
		int result = calcularFibonacci(n-1)+calcularFibonacci(n-2);
		return result;
	}

	public int countNotNull(Object[] array) {
		return contemNull(array, 0);
	}
	
	public static int contemNull(Object[] array, int index) {
		if (index == array.length-1) {
			if (array[index] == null) return 1;
			else return 0;
		}
		if (array[index] == null) {
			return 1+contemNull(array, index+1);
		}
		return contemNull(array, index+1); 
	}

	public long potenciaDe2(int expoente) {
		long result = 1;
		if (expoente == 0) {
			// caso base: nao faz nada. ADMININDO QUE expoente EH UM NATURAL!!!
		} else {
			result = 2 * potenciaDe2(expoente - 1);
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if (n == 1) {
			// caso base: nao faz nada
		} else {
			result = progressaoAritmetica(termoInicial, razao, n - 1) + razao;
		}
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if (n == 1) {
			// caso base: nao faz nada
		} else {
			result = progressaoAritmetica(termoInicial, razao, n - 1) * razao;
		}
		return result;
	}
	
	
}
