import java.util.Scanner;

class PilhaComArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int filaSize = Integer.parseInt(sc.nextLine());
		Pilha p = new Pilha(filaSize);
		while (true) {
			String entrada = sc.nextLine();
			if (entrada.equals("print")) {
				System.out.println(p.toString());
			}
			else if (entrada.equals("peek")) {
				p.peek();
			}
			else if (entrada.equals("pop")) {
				p.pop();
			}
			else if (entrada.contains("push")) {
				int valor = Integer.parseInt(entrada.split(" ")[1]);
				p.push(valor);
			}
			else if (entrada.equals("end")) {
				break;
			}
		}
		sc.close();
	}
}

class Pilha {
	private int[] array;
	private int top;
	public Pilha(int size) {
		array = new int[size];
		top = -1;
	}
	
	public void peek() {
		if (isEmpty()) System.out.println("empty");
		else System.out.println(array[top]);
	}
	
	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
		return (top == array.length-1);
	}
	
	public void push(int element) {
		if (isFull()) {
			System.out.println("full");
		}
		else array[++top] = element;
	}
	
	public void pop() {
		if (!isEmpty()) {
			top--;
		}
		else System.out.println("empty");
	}
	
	@Override
	public String toString() {
    	if (isEmpty()) return "empty";
		String saida = "";
		for (int i = 0; i < top; i++) {
			saida += array[i]+" ";
		}
		saida += array[top];
		return saida;
	}
}