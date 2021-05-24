import java.util.Scanner;

class PotenciaRecursiva {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		sc.nextLine();
		int num2 = sc.nextInt();
		sc.nextLine();
		sc.close();
		System.out.println(potencia(num1, num2));
	}
	
	public static int potencia(int num1, int num2) {
		if (num2 == 1) return num1;
		if (num2 == 0) return 1;
		return num1*potencia(num1, num2-1);
	}
}
