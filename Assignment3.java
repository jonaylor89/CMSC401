
import java.util.Scanner;

class Assignment3 {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] argv) {

        int numOfCities = in.nextInt();
        in.nextLine();

        int numOfHighways = in.nextInt();
        in.nextLine();

        int[] motelPrices = new int[numOfCities - 2];
        for (int i = 0; i <= motelPrice.length; i++) {
            int cityNumber = in.nextInt();
            in.nextLine();

            motelPrice[i] = in.nextInt();
            int.nextLine();
        }

        ArrayList<int[]> gasPrices = new ArrayList<int[]>();
        while (in.hasNextLine()) {
            int cityOne = in.nextInt();
            int cityTwo = in.nextInt();
            int price = in.nextInt();

            gasPrices.add(new int[]{cityOne, cityTwo, price});
        }


    
    }

}
