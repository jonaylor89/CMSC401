
/***********************************
 * John Naylor
 * CMSC 401
 * Algorithm Analysis
 **********************************/

import java.util.Scanner;

public class TrialAssignment {

    static Scanner userInput;

    public static void main(String[] argv) {

        int numOfLines;
        int firstIndex;
        int secondIndex;
        int product;
        int[] products;
        String lineStr;
        String[] line;
    
        userInput = new Scanner(System.in);

        numOfLines = userInput.nextInt();
        userInput.nextLine();

        products = new int[numOfLines];
        
        for (int i = 0; i < numOfLines; i++) {

            lineStr = userInput.nextLine();
            
            line = lineStr.split(" ");

            firstIndex = Integer.valueOf(line.length) - 1;
            secondIndex = Integer.valueOf(line.length) - 2;

            products[i] = Integer.valueOf(line[firstIndex]) * Integer.valueOf(line[secondIndex]);

        }

        for (int i = 0; i < numOfLines; i++) {
            System.out.println(products[i]); 
        }
    
    }

}
