
import java.util.Scanner;
import java.util.ArrayList;

public class cmsc401 {

    static Scanner in = new Scanner(System.in);
    static ArrayList<Integer> cuts = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> dp = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> parent = new ArrayList<>();
    static ArrayList<Integer> ans = new ArrayList<Integer>();

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

        System.out.println(cutRod(cuttingPoints, rodSize));

    }

    static int cutRod(ArrayList<Integer> points, int rodLength) {

        points.add(0, 0);
        points.add(rodLength);

        for (int i = 0; i < points.size(); i++) {
            cuts.add(points.get(i));
        }

        dp.ensureCapacity(points.size());
        parent.ensureCapacity(points.size());

        for (int i = 0; i < points.size(); i++) {
            dp.ensureCapacity(points.size());
            parent.ensureCapacity(points.size());

            for (int j = 0; j < points.size(); j++) {
                dp.get(i).set(j, -1);
            }
        }

        int best = C(0, points.size()-1);
        back(0, points.size()-1);


        return 0;
    }

    static int C(int i, int j) {

        if (i+1 >= j) {
            return 0; 
        }

        int ret = dp.get(i).get(j);

        if (ret != -1) {
            return ret; 
        }

        int bestind = 0;

        for(int z = i + 1; z < j; z++) {
            int p = C(i, z) + C(z, j) + cuts.get(j) - cuts.get(i);

            if(p < ret) {
                ret = p;
                bestind = z;
            }
        }

        parent.get(i).set(j, bestind);

        return ret;
    }
    

    static void back(int i, int j) {

        if (i+1 >= j) {
            return; 
        }

        ans.add(cuts.get(parent.get(i).get(j)));
        
        back(i, parent.get(i).get(j));
        back(parent.get(i).get(j), j);

    }

}
