package org.example; /**
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel {


    // An array to hold the lines_lengths to be sorted
    public int[] lines_lengths;
    //The amount of lines needed
    public final int total_number_of_lines = 256;
    // An array to holds the scrambled lines_lengths
    public int[] scramble_lines;
    //A temp Array that is used later for sorts
    public int[] tempArray;

    //the default constructor for the SortShow class
    public SortShow() {
        //assigning the size for the lines_lengths below
        lines_lengths = new int[total_number_of_lines];
        for (int i = 0; i < total_number_of_lines; i++)
            lines_lengths[i] = i + 5;

    }


    //A method that scrambles the lines
    public void scramble_the_lines() {
        //A random generator
        Random num = new Random();
        //Randomly switching the lines
        for (int i = 0; i < total_number_of_lines; i++) {
            //getting a random number using the nextInt method (a number between 0 to i + 1)
            int j = num.nextInt(i + 1);
            //swapping The element at i and j
            swap(i, j);
        }
        //assigning the size for the scramble_lines below
        scramble_lines = new int[total_number_of_lines];
        //copying the now scrambled lines_lengths array into the scramble_lines array
        //to store for reuse for other sort methods
        //so that all sort methods will use the same scrambled lines for fair comparison
        for (int i = 0; i < total_number_of_lines; i++) {
            scramble_lines[i] = lines_lengths[i];
        }
        //Drawing the now scrambled lines_lengths
        paintComponent(this.getGraphics());
    }

    //Swapping method that swaps two elements in the lines_lengths array
    public void swap(int i, int j) {
        //storing the i element in lines_lengths in temp
        int temp = lines_lengths[i];
        //giving i element in lines_lengths the value of j element in lines_lengths
        lines_lengths[i] = lines_lengths[j];
        //giving j element in lines_lengths the value of temp
        lines_lengths[j] = temp;
    }

    //////////////////////////////// BUBBLE SORT ///////////////////////////////////////////////////

    public void BubbleSort(){
        Calendar start = Calendar.getInstance();
        BubbleSort(total_number_of_lines);
        Calendar end = Calendar.getInstance();
        SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();

    }
    public void BubbleSort(int n){
       if(n>1){
           for(int i=0;i<n-1;i++){
               if(lines_lengths[i]>lines_lengths[i+1]){
                   swap(i,i+1);
               }
           }
           paintComponent(this.getGraphics());
           delay(10);

           BubbleSort(n-1);
       }
    }


    //////////////////////////////// SELECTION SORT ///////////////////////////////////////////////////
    //The selectionSort method
    public void SelectionSort() {
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();
        //Using the selection sort to lines_lengths sort the array

        //You need to complete this part.
        for (int index = 0; index < total_number_of_lines - 1; index++) {
            //redrawing the lines_lengths
            int indexOfNextSmallest = getIndexOfSmallest(index, total_number_of_lines - 1);
            swap(index, indexOfNextSmallest);
            paintComponent(this.getGraphics());
            delay(10);
        }

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //this method gets the smallest element in the array of lines_lengths
    public int getIndexOfSmallest(int first, int last) {
        int min = lines_lengths[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++) {
            if (lines_lengths[index] < min) {
                min = lines_lengths[index];
                indexOfMin = index;
            }
        }

        return indexOfMin;
    }


    //////////////////////////////// INSERTION SORT ///////////////////////////////////////////////////
    public void InsertionSort(){
        Calendar start = Calendar.getInstance();
        insertionSort(0,total_number_of_lines-1);
        Calendar end = Calendar.getInstance();
        SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    public void insertionSort(int first,int last){
        if(first<last){
            insertionSort(first,last-1);
            insertInOrder(lines_lengths[last],first,last-1);
            paintComponent(this.getGraphics());
            delay(10);
        }
    }

    public void insertInOrder(int removedElement,int begin,int end){
        if(removedElement>=lines_lengths[end]){
            lines_lengths[end+1]=removedElement;
        }
        else{
            lines_lengths[end+1]=lines_lengths[end];
            if(begin<end){
                insertInOrder(removedElement,begin,end-1);
            }
            else{
                lines_lengths[end]=removedElement;
            }
        }
    }



    //QUICK SORT

    public void QuickSort(){
        Calendar start = Calendar.getInstance();
        quicksort(lines_lengths,0,total_number_of_lines-1);
        Calendar end = Calendar.getInstance();
        SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();

    }

    // The main quicksort method
    private void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            paintComponent(this.getGraphics());
            delay(21);
            quicksort(arr, low, pivotIndex - 1); // Sort the left part
            quicksort(arr, pivotIndex + 1, high); // Sort the right part
        }
    }

    // Partition method used by quicksort
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Index of smaller element for tracking
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }


    ///////////////////////////////////////////////////////////////////////////////////


    //SHELL SORT

    public void ShellSort(){
        Calendar start = Calendar.getInstance();
        shellSort(lines_lengths);
        Calendar end = Calendar.getInstance();
        SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();

    }


    public void shellSort(int[] arr) {
        int n = arr.length;

        // Start with a gap n/2, then reduce the gap half everytime
        for (int gap = n / 2; gap > 0; gap --) {
            paintComponent(this.getGraphics());
            delay(20);

            // Insertion sort for the gap size
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////

    //recursive merge sort method
    public void R_MergeSort() {
        //getting the date and time when the recursive merge sort starts
        Calendar start = Calendar.getInstance();
        //assigning the size for the tempArray below

        //You need to complete this part.
        tempArray = new int[total_number_of_lines];

//        System.out.println(Arrays.toString(lines_lengths));
        R_MergeSort(0,total_number_of_lines-1);
//        System.out.println(Arrays.toString(tempArray));

        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();

    }

    //recursive merge sort method
    public void R_MergeSort(int first, int last) {
        if (first < last) {
            int middle=(first+last)/2;

            //sort first and second half
            R_MergeSort(first,middle);
            R_MergeSort(middle+1,last);

            // merge
            R_Merge(first,middle,last);
            paintComponent(this.getGraphics());

            //Causing a delay for 10ms
            delay(10);
        }
    }

    //recursive merge sort method
    public void R_Merge(int first, int mid, int last) {
        int beginFirstHalf= first;
        int beginSecondHalf=mid+1;
        int index=first;
        while(beginFirstHalf<=mid && beginSecondHalf<=last){
           if(lines_lengths[beginFirstHalf]<lines_lengths[beginSecondHalf]){
               tempArray[index]=lines_lengths[beginFirstHalf];
               beginFirstHalf++;
           }
           else{
               tempArray[index]=lines_lengths[beginSecondHalf];
               beginSecondHalf++;
           }
           index++;
        }

        //copy remaining elements from first half
        while(beginFirstHalf<=mid){
            tempArray[index]=lines_lengths[beginFirstHalf];
            beginFirstHalf++;
            index++;
        }

        //copy remaining elements from second half
        while(beginSecondHalf<=last){
            tempArray[index]=lines_lengths[beginSecondHalf];
            beginSecondHalf++;
            index++;
        }

        //copy elements from temp array to original array
        for (int i = first; i <= last; i++) {
            lines_lengths[i] = tempArray[i];
        }

    }

    //

    //////////////////////////////////////////////////////////////////////////////////////////

    //iterative merge sort method
    public void I_MergeSort() {
        //getting the date and time when the iterative merge sort starts
        Calendar start = Calendar.getInstance();
        //assigning the size for the tempArray below
        tempArray = new int[total_number_of_lines];
        //saving the value of total_number_of_lines
        int beginLeftovers = total_number_of_lines;


        for (int segmentLength = 1; segmentLength <= total_number_of_lines / 2; segmentLength = 2 * segmentLength) {
            beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
            int endSegment = beginLeftovers + segmentLength - 1;
            if (endSegment < total_number_of_lines - 1) {
                I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
            }
        }

        // merge the sorted leftovers with the rest of the sorted array
        if (beginLeftovers < total_number_of_lines) {
            I_Merge(0, beginLeftovers - 1, total_number_of_lines - 1);
        }
        //getting the date and time when the iterative merge sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
    }

    // Merges segments pairs (certain length) within an array
    public int I_MergeSegmentPairs(int l, int segmentLength) {
        //The length of the two merged segments

        //You suppose  to complete this part (Given).
        int mergedPairLength = 2 * segmentLength;
        int numberOfPairs = l / mergedPairLength;

        int beginSegment1 = 0;
        for (int count = 1; count <= numberOfPairs; count++) {
            int endSegment1 = beginSegment1 + segmentLength - 1;

            int beginSegment2 = endSegment1 + 1;
            int endSegment2 = beginSegment2 + segmentLength - 1;
            I_Merge(beginSegment1, endSegment1, endSegment2);

            beginSegment1 = endSegment2 + 1;
            //redrawing the lines_lengths
            paintComponent(this.getGraphics());
            //Causing a delay for 10ms
            delay(10);
        }
        // Returns index of last merged pair
        return beginSegment1;
        //return 1;//modify this line
    }

    public void I_Merge(int first, int mid, int last) {
        //You suppose  to complete this part (Given).
        // Two adjacent sub-arrays
        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        // While both sub-arrays are not empty, copy the
        // smaller item into the temporary array
        int index = beginHalf1; // Next available location in tempArray
        for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++) {
            // Invariant: tempArray[beginHalf1..index-1] is in order
            if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2]) {
                tempArray[index] = lines_lengths[beginHalf1];
                beginHalf1++;
            } else {
                tempArray[index] = lines_lengths[beginHalf2];
                beginHalf2++;
            }
        }
        //redrawing the lines_lengths
        paintComponent(this.getGraphics());

        // Finish off the nonempty sub-array

        // Finish off the first sub-array, if necessary
        for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
            // Invariant: tempArray[beginHalf1..index-1] is in order
            tempArray[index] = lines_lengths[beginHalf1];

        // Finish off the second sub-array, if necessary
        for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
            // Invariant: tempa[beginHalf1..index-1] is in order
            tempArray[index] = lines_lengths[beginHalf2];

        // Copy the result back into the original array
        for (index = first; index <= last; index++)
            lines_lengths[index] = tempArray[index];
    }

    //////////////////////////////////////////////////////////////////////

    //This method resets the window to the scrambled lines display
    public void reset() {
        if (scramble_lines != null) {
            //copying the old scrambled lines into lines_lengths
            for (int i = 0; i < total_number_of_lines; i++) {
                lines_lengths[i] = scramble_lines[i];
            }
            //Drawing the now scrambled lines_lengths
            paintComponent(this.getGraphics());
        }
    }


    //This method colours the lines and prints the lines
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //A loop to assign a colour to each line
        for (int i = 0; i < total_number_of_lines; i++) {
            //using eight colours for the lines
            if (i % 8 == 0) {
                g.setColor(Color.green);
            } else if (i % 8 == 1) {
                g.setColor(Color.blue);
            } else if (i % 8 == 2) {
                g.setColor(Color.yellow);
            } else if (i % 8 == 3) {
                g.setColor(Color.red);
            } else if (i % 8 == 4) {
                g.setColor(Color.black);
            } else if (i % 8 == 5) {
                g.setColor(Color.orange);
            } else if (i % 8 == 6) {
                g.setColor(Color.magenta);
            } else
                g.setColor(Color.gray);

            //Drawing the lines using the x and y-components
            g.drawLine(7 * i + 25, 500, 7 * i + 25, 500 - lines_lengths[i]);
        }

    }

    //A delay method that pauses the execution for the milliseconds time given as a parameter
    public void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}

