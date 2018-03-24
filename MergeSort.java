import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MergeSort {
	
	//Class to manipulate the comparison function
	static class Input implements Comparable<Input> {
        static long comparison_num;

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
	
	//Function to sort and merge two arraylists
	public ArrayList<Input> merge(ArrayList<Input> l1, ArrayList<Input> l2){
		int n1 = l1.size();
		int n2 = l2.size();
		ArrayList<Input> al = new ArrayList<Input>();
		int n = n1 + n2;
		int c1 = 0, c2 = 0;
		//Loop till one of the list gets empty
		while(c1 != n1 && c2 != n2) {
			Input b = l2.get(c2);
			Input a = l1.get(c1);
			if(b.compareTo(a)<0) {
				al.add(b);
				c2++;
			}else {
				al.add(a);
				c1++;
			}
		}
		//conditional to append the elements of the non-empty list
		if(c2 == n2) {
			while(c1 != n1) {
				al.add(l1.get(c1));
				c1++;
			}
		}else if(c1 == n1) {
			while(c2 != n2) {
				al.add(l2.get(c2));
				c2++;
			}
		}
		return al;
	}
	
	//Main recursive function that divides the given input, sorts it and merges it
	public ArrayList<Input> sort(ArrayList<Input> list){
		int first = 0, last = list.size()-1;
		//return if the list has 1 or less elements
		if(last <= first) {
			return list;
		}
		//take middle element to be the rounded off value to the next integer
		int mid = (last + first + 1) / 2;
		ArrayList<Input> list1 = new ArrayList<Input>();
		ArrayList<Input> list2 = new ArrayList<Input>();
		for(int i=first; i<mid; i++) {
			list1.add(list.get(i));
		}
		for(int i=mid; i<=last; i++) {
			list2.add(list.get(i));
		}
		//Recursive calls after dividing the problem
		list1 = sort(list1);
		list2 = sort(list2);
		//Merging the subproblems
		ArrayList<Input> mylist = merge(list1, list2);
		return mylist;
	}
	
	//Function to display the List
	public void displayList(ArrayList<Input> l) {
		Iterator<Input> itr = l.iterator();
		while(itr.hasNext()) {
			System.out.print("\n" + (itr.next()).value );
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Input> arr = new ArrayList<Input>();
		Scanner sc = new Scanner(System.in);
		//Extracting the number of elements from the first line of the input
		String str = sc.nextLine();
		int size = Integer.parseInt(str.substring(2));
		//Taking the inputs
		for(int i=0; i<size; i++) {
			arr.add(new Input(sc.nextInt()));
		}
		MergeSort obj = new MergeSort();
		//Noting the starting time of the run
		int start_time = (int)System.currentTimeMillis();
		//Running the sorting algorithm
		ArrayList<Input> al = obj.sort(arr);
		//Noting the ending time of the run
		int end_time = (int)System.currentTimeMillis();
		//Printing required data to the standard error
		System.err.print("runtime," + (end_time - start_time));	
		System.err.print("\ncomparisons," + Input.comparison_num);
		//Displaying the output - sorted list
		obj.displayList(al);
	}
	
}
