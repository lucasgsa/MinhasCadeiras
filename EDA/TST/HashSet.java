import java.util.Arrays;
import java.util.Scanner;

class HashSet {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		HashSet hs = new HashSet(size);
		while (true) {
			String[] entrada = sc.nextLine().split(" ");
			if (entrada[0].equals("put")) {
				hs.put(Integer.parseInt(entrada[1]));
				hs.print();
			}
			else if (entrada[0].equals("remove")) {
				hs.remove(Integer.parseInt(entrada[1]));
				hs.print();
			}
			else if (entrada[0].equals("contains")) {
				System.out.println(hs.contains(Integer.parseInt(entrada[1])));
			}
			else if (entrada[0].equals("end")) {
				break;
			}
		}
	}
	
	Integer[] array;
	int elements;
	
	public HashSet(int size) {
		array = new Integer[size];
		elements = 0;
	}
	
	public int search(Integer valor) {
		int initHash = hash(valor);
		if (array[initHash] != null && array[initHash].equals(valor)) return initHash;
		int prob = 1;
		while (true) {
			int atualHash = hash(valor+prob);
			if (atualHash == initHash) return -1;
			if (array[atualHash] == null) {
				prob++;
				continue;
			}
			if (array[atualHash].equals(valor)) return atualHash;
			prob++;
		}
	}
	
	public boolean contains(Integer valor) {
		return (search(valor)) != -1;
	}
	
	public void remove(Integer valor) {
		int index = search(valor);
		if (index == -1) return;
		array[index] = null;
	}
	
	public void put(Integer valor) {
		if (contains(valor)) return;
		int initHash = hash(valor);
		if (array[initHash] == null) {
			array[initHash] = valor;
		}
		else {
			int prob = 1;
			while (true) {
				int atualHash = hash(valor+prob);
				if (atualHash == initHash) break;
				if (array[atualHash] == null) {
					array[atualHash] = valor;
					break;
				}
				else {
					prob++;
				}
			}
		}
	}
	
	public void print() {
		System.out.println(Arrays.toString(array));
	}
	
	public int hash(int key) {
		return key%array.length;
	}
}
