import java.util.Scanner;

class TeoremaMestre {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		System.out.println("T(n) = theta("+teoremaMestre(a, b, c)+")");
	}
	
	public static String teoremaMestre(int a, int b, int c) {
		double log = Math.log(a)/Math.log(b);
		if (c < log) {
			return "n**"+log;
		}
		else if (c == log) {
			return "n**" + c + " * log n";
		}
		else {
			return "n**" + c;
		}
	}
}
