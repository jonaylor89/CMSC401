
import java.util.Scanner;
import java.util.ArrayList;

public class cmsc401 {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] argv) {
    
        int rodSize = in.nextInt();
        in.nextLine();

        int numOfCuttingPoints = in.nextInt();
        in.nextLine();

        ArrayList<Integer> cuttingPoints = new ArrayList<Integer>();

        for (int i = 0; i < numOfCuttingPoints; i++) {
            cuttingPoints.add(in.nextInt());
            in.nextLine();
        }

        System.out.println(cutRod(cuttingPoints));

    }

    static int cutRod(ArrayList<Integer> points) {
        return 0; 
    }

    // Cost of cutting the rod from poitn i to point j
    static int C(int i, int j) {
        return 0; 
    }

}
