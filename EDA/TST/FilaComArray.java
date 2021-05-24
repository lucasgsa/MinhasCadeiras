import java.util.Scanner;

class FilaComArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int filaSize = Integer.parseInt(sc.nextLine());
		Fila f = new Fila(filaSize);
		while (true) {
			String entrada = sc.nextLine();
			String[] comando = entrada.split(" ");
			if (comando[0].equals("print")) {
				System.out.println(f.toString());
			}
			else if (comando[0].equals("element")) {
				f.element();
			}
			else if (comando[0].equals("remove")) {
				f.remove();
			}
			else if (comando[0].equals("end")) {
				break;
			}
			else if (comando[0].equals("add")){
				int valor = Integer.parseInt(comando[1]);
				f.add(valor);
			}
		}
		sc.close();
	}
}
class Fila {
	private int[] array;
	private int tail;
	
    public Fila(int size) {
		array = new int[size];
		tail = -1;
	}
    public boolean isEmpty() {
		return tail == -1;
    }

    public boolean isFull() {
		return tail == array.length-1;
    }
    public void add(int n) {
        if (isFull()) System.out.println("full");
        else {
        	array[tail+1] = n;
        	tail++;
        }
    }
	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i+1];
		}
	}
    public void remove() {
        if (isEmpty()) System.out.println("empty");
        else {
        	shiftLeft();
        	tail--;
		}
        
    }

    public void element() {
    	if (isEmpty()) System.out.println("empty");
    	else System.out.println(array[0]);
    }
    
    @Override
    public String toString() {
    	if (isEmpty()) return "empty";
		String saida = "";
		for (int i = 0; i <= tail; i++) {
			if (i == tail) saida += array[i];
			else saida += array[i]+" ";
		}
		return saida;
    }
}
