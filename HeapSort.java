import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class HeapSort {
	
	//Class to manipulate the comparison function
	static class Input implements Comparable<Input> {
        static long comparison_num=0;

        public Integer value;
        Input(int val){
            this.value = val;
        }
        @Override
        public int compareTo(Input o) {
            comparison_num++;
            return Integer.compare(value,o.value);
        }
    }
	
	//Function to construct heap using the input array
	public void build_max_heap(ArrayList<Input> list) {
		//Variable to store size of the heap
		int l = list.size();
		//Loop that creates the heap starting from the element in the middle i.e. element with least children
		for(int i=l/2; i>0; i--) {
			heapify(list, i-1);
		}
	}
	
	//function to create heap
	public void heapify(ArrayList<Input> list, int idx) {
		//Varible to store size of the heap
		int l = list.size();
		//Variables to store left and right indexes of the element passed to the function
		int leftidx = 2*idx + 1, rightidx = 2*idx + 2;
		//Variable to store the index of the largest element of the (idx, leftidx, and rightidx)
		int largest = idx;
		//setting largest element to be left if it is greater than the element at idx.
		if(leftidx<=l-1 && list.get(leftidx).compareTo(list.get(idx))>0) {
			largest = leftidx;
		}
		//setting largest element to be right if it is greater than the element at idx.
		if(rightidx<=l-1 && list.get(rightidx).compareTo(list.get(largest))>0) {
			largest = rightidx;
		}
		//Swapping the largest element and the element at idx if they are different
		if(largest != idx) {
			Input temp = list.get(idx);
			list.set(idx, list.get(largest));
			list.set(largest, temp);
			heapify(list, largest);
		}
	}
	
	//Function to remove and return the root element of the heap and then recreating the heap
	public Input remove(ArrayList<Input> list) {
		//Variable to store the root element of heap
		Input x = list.get(0);
		//Setting the 0th element to be the last element of the heap
		list.set(0, list.get(list.size()-1));
		//Reducing the size of heap by 1
		list.remove(list.size()-1);
		//Recreating the heap
		heapify(list,0);
		//returning the root element
		return x;
	}
	
	//Controlling function for heapsort algorithm
	public ArrayList<Input> heapSort(ArrayList<Input> al){
		//Creating list to store the sorted elements
		ArrayList<Input> mylist = new ArrayList<Input>();
		while(al.size()>0) {
			//adding the root element of the heap to the first position in the sorted list.
			mylist.add(0, this.remove(al));
		}
		return mylist;
	}
	
	//Function to display the list
	public void displayList(ArrayList<Input> l) {
		Iterator<Input> itr = l.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().value);
		}
	}
	
	public static void main(String[] args) {
		//List to store input
		ArrayList<Input> arr = new ArrayList<Input>();
		HeapSort obj = new HeapSort();
		//Taking the input
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		//Extracting the number of elements from the first line of input
		int size = Integer.parseInt(str.substring(2));
		//Storing the input elements
		for(int i=0; i<size; i++) {
			arr.add(new Input(sc.nextInt()));
		}
		int start_time = (int) System.currentTimeMillis();
		//Building heap using the input array
		obj.build_max_heap(arr);
		//Applying heapsort algorithm to the maxheap
		ArrayList<Input> al = obj.heapSort(arr);
		int end_time = (int)System.currentTimeMillis();
		//Displaying required information
		obj.displayList(al);
		System.err.println("runtime," + (end_time - start_time));
		System.err.print("comparisons," + Input.comparison_num);
	}

}
