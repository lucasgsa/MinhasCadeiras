import java.util.Scanner;

class WarmUp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int multiplicador = sc.nextInt();
		sc.nextLine();
		int[] array = converteArray(sc.nextLine().split(" "));
		multiplicadorArray(array, multiplicador);
	}
	
	public static void multiplicadorArray(int[] array, int x) {
		for (int i = 0; i < array.length; i++) {
			if (i == array.length-1) {
				System.out.println(array[i]*x);
			}
			else {
				System.out.print(array[i]*x+" ");	
			}
		}
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}
