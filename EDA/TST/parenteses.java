import java.util.Scanner;

class parenteses {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] entrada = sc.nextLine().split("");
		PilhaParenteses pilha = new PilhaParenteses();
		String saida = "S";
		for (int i = 0; i < entrada.length; i++) {
			String tipo = entrada[i];
			if (tipo.equals("(")) {
				pilha.add("(");
			}
			else {
				if (pilha.isEmpty()) {
					saida = "N";
				}
				pilha.remover();
			}
		}
		if (!pilha.isEmpty()) {
			saida = "N";
		}
		System.out.println(saida);
	}
}

class PilhaParenteses {
	 NodePilha first;
	 public boolean isEmpty() {
		 return first == null;
	 }
	 public void add(String value) {
		 if (isEmpty()) {
			 first = new NodePilha();
			 first.data = value;
		 }
		 else {
			 NodePilha atual = first;
			 while (atual.next != null) {
				 atual = atual.next;
			 }
			 atual.next = new NodePilha();
			 atual.next.data = value;
		 }
	 }
	 
	 public boolean remover() {
		 if (isEmpty()) return false;
		 if (first.next == null) {
			 first = null;
			 return true;
		 }
		 NodePilha atual = first;
		 while (atual.next.next != null) {
			 atual = atual.next;
		 }
		 atual.next = null;
		 return true;
	 }
}

class NodePilha {
	public String data;
	public NodePilha next;
}
