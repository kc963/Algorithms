import java.util.*;

public class UtilitySort {

  //Class to manipulate the comparison function
  	static class Input implements Comparable<Input> {
  		//Long to record number of comparisons
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

  	//Method to display the list
    public static void displayList(ArrayList<Input> l) {
        Iterator<Input> itr = l.iterator();
        while (itr.hasNext()) {
            System.out.println((itr.next()).value);
        }
    }

    public static void main(String args[]) {
        ArrayList<Input> arr = new ArrayList<Input>();
        Scanner sc = new Scanner(System.in);
        //Extracting number of input values from the first input line
        String str = sc.nextLine();
        int size = Integer.parseInt(str.substring(2));
        //Receiving inputs
        for (int i = 0; i < size; i++) {
        	//Adding received value to an array
            arr.add(new Input(sc.nextInt()));
        }
        //Recording the start time of the sorting algorithm
        int startTime = (int)System.currentTimeMillis();
        //Sorting the inputs stored in the array using sorting utility
        Collections.sort(arr);
        //Recording the duration for which the algorithm ran
        int duration = (int)System.currentTimeMillis() - startTime;
        //Displaying output - sorted array
        displayList(arr);
        //Printing required data to the standard error
        System.err.println("runtime," + duration);
        System.err.print("comparisons,"+ Input.comparison_num);
    }

}

