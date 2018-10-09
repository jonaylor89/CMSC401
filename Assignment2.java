
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Assignment2 {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] argv) {

        ArrayList<Integer> yCoods = new ArrayList<Integer>();
    
        int numberOfHouses = in.nextInt();
        in.nextLine();

        for (int i = 0; i < numberOfHouses; i++) {
            yCoods.add(in.nextInt());
            in.nextLine();
        }


        int median = (int) calculateMedian(yCoods);

        System.out.println(median);

    }

    public static double calculateMedian(ArrayList<Integer> unsortedList) {

        if (unsortedList.size() % 2 == 1) {
            return quickselect(unsortedList, unsortedList.size() / 2);
        } else {
            return 0.5 * (quickselect(unsortedList, unsortedList.size() / 2 - 1) + 
                            quickselect(unsortedList, unsortedList.size() / 2));
        }

    }

    public static int quickselect(ArrayList<Integer> unsortedList, int key) {

        Random r = new Random();
    
        if (unsortedList.size() == 1) {
            return unsortedList.get(0); 
        }

        int pivotIndex = r.nextInt(unsortedList.size());
        int pivot = unsortedList.get(pivotIndex);
       
        ArrayList<Integer> lows = new ArrayList<Integer>();
        ArrayList<Integer> highs = new ArrayList<Integer>();
        ArrayList<Integer> pivots = new ArrayList<Integer>();

        for (Integer i : unsortedList) {
            if (i < pivot) 
                lows.add(i);
            else if (i > pivot)
                highs.add(i);
            else
                pivots.add(i);
        }


        if (key < lows.size()) {
            return quickselect(lows, key);
        } else if (key < lows.size() + pivots.size()) {
            return pivots.get(0); 
        } else {
            return quickselect(highs, key - lows.size() - pivots.size());
        }
    }
}
















