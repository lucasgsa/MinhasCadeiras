import java.util.Scanner;

class FilaLinkedList {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FilaLinked f = new FilaLinked();
		while (true) {
			String entrada = sc.nextLine();
			String[] comando = entrada.split(" ");
			if (comando[0].equals("print")) {
				f.printer();
			}
			else if (comando[0].equals("element")) {
				Integer retorno = f.head();
				if (retorno == null) System.out.println("empty");
				else System.out.println(retorno);
			}
			else if (comando[0].equals("remove")) {
				if (!f.remove()) System.out.println("empty");
			}
			else if (comando[0].equals("search")) {
				System.out.println(f.search(Integer.parseInt(comando[1])));
			}
			else if (comando[0].equals("add")){
				int valor = Integer.parseInt(comando[1]);
				f.add(valor);
			}
			else if (comando[0].equals("end")) {
				break;
			}
		}
		sc.close();
	}
	
	public static void printarString(String string) {
		if (string.equals("empty")) System.out.println(string);
		else System.out.println(string.substring(1, string.length()-1).replaceAll(",", ""));
	}
}

class FilaLinked {
	Node first;
	Node last;
	int size = 0;
	
	public int search(Integer elemento) {
		return buscaElemento(first, elemento, 0);
	}
	
	public int buscaElemento(Node no,Integer elemento, int index) {
		if (no == null) return -1;
		if (no.data.equals(elemento)) {
			return index;
		}
		return buscaElemento(no.next, elemento, index+1);
	}
	
	public Integer head() {	
		if (first == null) return null;
		return first.data;
	}
	public void add(Integer element) {
		Node node = new Node();
		node.data = element;
		if (first == null & last == null) {
			first = node;
			last = node;
		}
		else {
			last.next = node;
			last = node;
		}
		size++;
	}
	public boolean remove() {
		if (first == null & last == null) return false;
		else if (first == last) {
			first = null;
			last = null;
		}
		else {
			first = first.next;
		}
		size--;
		return true;
	}

	public void printer() {
		if (first == null) {
			System.out.println("empty");
			return;
		}
		recursivePrinter(first);
	}
	
	public void recursivePrinter(Node no) {
		if (no.next == null) {
			System.out.println(no.data);
		}
		else {
			System.out.print(no.data+" ");
			recursivePrinter(no.next);
		}
	}
}

class Node {
	public Integer data;
	public Node next;
	public Node previous;
}
