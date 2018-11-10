
import java.util.Scanner;
import java.util.HashMap;

class Assignment3 {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] argv) {

        int numOfCities = in.nextInt();
        in.nextLine();

        int numOfHighways = in.nextInt();
        in.nextLine();

        HashMap<Integer, Integer> motelPrices = new HashMap<Integer, Integer>();
        for (int i = 0; i <= motelPrice.length; i++) {
            int cityNumber = in.nextInt();

            int motelPrice = in.nextInt();
            int.nextLine();

            motelPrices.put(cityNumber, motelPrice);
        }

        HashMap<Integer[], Integer> gasPrices = new HashMap<Integer[], Integer>();
        while (in.hasNextLine()) {
            int cityOne = in.nextInt();
            int cityTwo = in.nextInt();
            int price = in.nextInt();

            gasPrices.put(new int[]{cityOne, cityTwo}, price);
        }

    }

    int dijkstra() {
    
    }

}
