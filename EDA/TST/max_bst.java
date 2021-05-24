import java.util.Arrays;
import java.util.Scanner;

class max_bst {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BST bst = new BST();
		int[] entrada = converteArray(sc.nextLine().split(" "));
		sc.close();
		for (int e:entrada) {
			bst.insert(e);
		}
		bst.max();
	}
	
	public static int[] converteArray(String[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
}

class BST {
	NodeBST root;

	public void max() {
		if (root == null) System.out.println();
		else {
			NodeBST n = root;
			while (n.right != null) {
				System.out.print(n.data+" ");
				n = n.right;
			}
			System.out.println(n.data);
		}
	}
	
	public void insert(Integer element) {
		if (root == null) {
			NodeBST n = new NodeBST();
			n.data = element;
			root = n;
			return;
		}
		insert(this.root, element);
	}
	private void insert(NodeBST node, Integer element) {
		if (element < node.data) {
			if (node.left == null) {
				NodeBST n = new NodeBST();
				n.data = element;
				n.parent = node;
				node.left = n;
				return;
			}
			else insert(node.left, element);
		}
		else {
			if (node.right == null) {
				NodeBST n = new NodeBST();
				n.data = element;
				n.parent = node;
				node.right = n;
				return;
			}
			else insert(node.right, element);
		}
	}
}

class NodeBST {
	Integer data;
	NodeBST left;
	NodeBST right;
	NodeBST parent;
}
