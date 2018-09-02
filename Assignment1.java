
/************************
 * John Naylor
 * CMSC 401
 * Assignment 1
 ***********************/

import java.util.Scanner;

public class Assignment1 {

    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] argv) {
    
       int rows = userInput.nextInt();
       userInput.nextLine(); // Needed to get the newline character

       int[] arrayData = new int[rows];

       for (int i = 0; i < rows; i++) {
            arrayData[i] = userInput.nextInt();
            userInput.nextLine(); // Newline character
       }

        int candidateElement = calculateCandidate(arrayData);
        int MEElement = majorityElementOf(arrayData, candidateElement);

        System.out.println(MEElement);
    }

    static int calculateCandidate(int[] MEArray) {
    
        int candidate = MEArray[0];
        int counter = 1;

        for (int i = 1; i < MEArray.length-1; i++) {
            if (MEArray[i] == candidate) {
                counter++; 
            } else {
                counter--; 
            }

            if (counter == 0) {
                candidate = MEArray[i]; 
                counter = 1;
            }
        }

        return candidate;
    }

    static int majorityElementOf(int[] MEArray, int candidate) {

        int counter = 0;
        
        for (int elem : MEArray) {
           if (elem == candidate) {
                counter++; 
           }

           if (counter > MEArray.length/2) {
                return candidate; 
           }

        }

        // Fail Case
        return -1;
    }
}
