import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class InsertionSort{

	//Class to manipulate the comparison function
    static class Input implements Comparable<Input> {
        static long comparison_num; //Long to store number of comparisons

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

    //Sorting function
    public static ArrayList<Input> insertionSort(ArrayList<Input> mylist){
        //Recording start time
        final long startTime = System.currentTimeMillis();
        //Outer loop - loops from second element to last
        for(int i=1; i < mylist.size(); i++){
            Input x = mylist.get(i);
            int j = i - 1;
            //Inner loop - loops from (i-1)th element to the first element 
            while(j >= 0 && mylist.get(j).compareTo(x)> 0){
                mylist.set(j+1,mylist.get(j));
                j = j -1;
            }
            mylist.set(j+1,x);
        }
        //Recording the duration for which the sorting loops lasted
        final long duration = System.currentTimeMillis() - startTime;
        //Printing values to the standard output
        for(Input i: mylist){
            System.out.println(i.value);
        }
        //Printing the required values to the standard error
        System.err.println("runtime," + duration);
        System.err.print("comparisons,"+ Input.comparison_num);
        return mylist;
    }

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        //Extracting the number of inputs from the first input line
        String str = reader.nextLine();
        int size = Integer.parseInt(str.substring(2));
        ArrayList<Input> inpuArray = new ArrayList<Input>();
        //Entering data in the array
        for(int i = 0; i<size;i++){
            int tempNum = reader.nextInt();     // Read each number for array
            inpuArray.add(new Input(tempNum));
        }
        reader.close();
        //Running the sorting algorithm on the array
        insertionSort(inpuArray);
    }

}
