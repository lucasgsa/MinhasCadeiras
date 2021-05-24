import java.util.Arrays;
import java.util.Scanner;

class BuildHeap {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Integer[] array = converteArray(sc.nextLine().split(" "));
		Heap hp = new Heap(array);
		boolean equal = isEqual(array, hp.getArray());
		System.out.println(equal);
	}
	public static Integer[] converteArray(String[] array) {
		Integer[] newArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.parseInt(array[i]);
		}
		return newArray;
	}
	
	public static boolean isEqual(Integer[] a1, Integer[] a2) {
		for (int i = 0; i < a1.length; i++) {
			if (a1[i] != a2[i]) return false;
		}
		return true;
	}
}

class Heap {
	Integer[] heap;
	int tail;
	
	public Heap(Integer[] heap) {
        this.heap = Arrays.copyOf(heap, heap.length);
        this.tail = this.heap.length - 1;
        this.buildHeap();
    }
	
	public Integer[] getArray() {
		return heap;
	}
    
    private void buildHeap() {
        for (int i = parent(this.tail); i >= 0; i--)
            heapify(i); 
    }
	
    private void heapify(int index) {
        if (isLeaf(index) || !isValidIndex(index)) 
            return;

        int index_max = max_index(index, left(index), right(index));
        
        if (index_max != index) {
                swap(index, index_max);
                heapify(index_max);
        }
    }  
	
    private int max_index(int index, int left, int right) {
        if (this.heap[index] > this.heap[left]) {
            if (isValidIndex(right)) {
                if (this.heap[index] < this.heap[right])
                    return right;
            }
           
            return index;
        
        } else {
            if (isValidIndex(right)) {
                if (this.heap[left] < this.heap[right])
                    return right;
            } 
            
            return left;
        }
    }
	
    public int left(int index) {
        return 2 * index + 1;
    }

    public int right(int index) {
        return 2 * (index + 1);
    }

    public int parent(int index) {
        return (index-1)/2;
    }
    
    private boolean isValidIndex(int index) {
        return index >= 0 && index <= tail;
    }
    
    private boolean isLeaf(int index) {
        return index > parent(tail) && index <= tail;
    } 
    
    private void swap(int i, int j) {
        int aux = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = aux;
    }
}
