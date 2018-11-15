
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Assignment3 {

    // Scanner to get user input
    static Scanner in = new Scanner(System.in);

    // Temperary data structure to store nodes 
    static HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

    public static void main(String[] argv) {

        Graph g = new Graph();

        int numOfCities = in.nextInt();
        in.nextLine();

        int numOfHighways = in.nextInt();
        in.nextLine();

        for (int i = 0; i <= numOfCities; i++) {
            int cityNumber = in.nextInt();

            int motelPrice = in.nextInt();
            in.nextLine();

            nodes.put(cityNumber, new Node(cityNumber, motelPrice));
        }

        while (in.hasNextLine()) {
            int cityOne = in.nextInt();
            int cityTwo = in.nextInt();
            int price = in.nextInt();

            g.addEdge(new Edge(nodes.get(cityOne), nodes.get(cityTwo), price));
        }

        // Compute shortest path
        int[] distance = g.dijkstra();

        // Print result
        System.out.println(IntStream.of(distance).sum());

    }

    static class Graph {

        ArrayList<Edge> edges;
        PriorityQueue<Node> pq; 

        public Graph() {
            edges = new ArrayList<Edge>();
            pq = new PriorityQueue<Node>();
        }

        public void addEdge(Edge e) {
             
        }

        int[] dijkstra() {
            return new int[]{};
        }
    
    }

    static class Node {

        public int id;
        public int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    
    }

    static class Edge {

        public Node n1;
        public Node n2;
        public int weight;

        public Edge(Node n1, Node n2, int weight) {
            this.n1 = n1;
            this.n2 = n2;
            this.weight = weight;
        }
    
    }

}

























