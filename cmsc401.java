
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class cmsc401 {

    static Scanner in = new Scanner(System.in);
    static List<Integer> cuts = new ArrayList<Integer>();
    static List<Integer> ans = new ArrayList<Integer>();
    static List<ArrayList<Integer>> dp;
    static List<ArrayList<Integer>> parent;

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

        int ret = cutRod(cuttingPoints, rodSize);

        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }

    }

    static int cutRod(ArrayList<Integer> points, int rodLength) {

        points.add(0, 0);
        points.add(rodLength);

        for (int i = 0; i < points.size(); i++) {
            cuts.add(points.get(i));
        }

        dp = Stream.generate(ArrayList<Integer>::new)
                             .limit(points.size())
                             .collect(Collectors.toList());

        parent = Stream.generate(ArrayList<Integer>::new)
                             .limit(points.size())
                             .collect(Collectors.toList());

        for (int i = 0; i < points.size(); i++) {
            dp.set(i, new ArrayList<Integer>(Collections.nCopies(points.size(), -1)));
            parent.set(i, new ArrayList<Integer>(Collections.nCopies(points.size(), -1)));

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

        ret = Integer.MAX_VALUE;
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
