
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList; import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Assignment3 {

    static HashMap<Integer, Integer> motelPrices = new HashMap<Integer, Integer>();

    public static void main(String[] argv) {

        Scanner in = new Scanner(System.in);

        Graph g = new Graph();

        int numOfCities = in.nextInt();
        in.nextLine();

        int numOfHighways = in.nextInt();
        in.nextLine();


        motelPrices.put(0, 0);

        for (int i = 0; i <= numOfCities; i++) {
            int cityNumber = in.nextInt();

            int motelPrice = in.nextInt();
            in.nextLine();

            motelPrices.put(cityNumber, motelPrice);
        }

        while (in.hasNextLine()) {
            int cityOne = in.nextInt();
            int cityTwo = in.nextInt();
            int price = in.nextInt();

            g.addEdge(cityOne, motelPrices.get(cityOne), cityTwo, motelPrices.get(cityTwo), price);
        }

        // Compute shortest path
        Integer[] distance = g.dijkstra();

        // Print result
        System.out.println(IntStream.of(distance).sum());

    }

    static class Graph {

        int numOfVertices;
        ArrayList<Edge>[] adjacencyList;
        PriorityQueue<Node> pq; 

        public Graph() {
            numOfVertices = 0;
            adjacencyList = new ArrayList<Edge>[numOfVertices];
            pq = new PriorityQueue<Node>();

            for (int i = 0; i < numOfVertices; i++) {
                adjacencyList[i] = new ArrayList<Edge>();
            }
        }

        public Graph(int V) {
            numOfVertices = V;
            adjacencyList = new ArrayList<Edge>[numOfVertices];
            pq = new PriorityQueue<Node>();

            for (int i = 0; i < numOfVertices; i++) {
                adjacencyList[i] = new ArrayList<Edge>();
            }
        }

        public void addEdge(int src, int srcWeight, int dest, int destWeight, int weight) {

            Edge e = new Edge(src, srcWeight, dest, destWeight, weight);

            edges.add(e);
        }

        public Integer[] dijkstra() {
            int INFINITY = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[numOfVertices];

            Node[] nodes = new Node[numOfVertices];
            for (int i = 0; i < numOfVertices; i++) {
                nodes[i] = new Node(i, motelPrices.get(i), INFINITY);
            }

            //decrease the distance for the first index
            nodes[0].distance = 0;

            //add all the vertices to the MinHeap
            for (int i = 0; i < numOfVertices; i++) {
                pq.add(nodes[i]);
            }
            //while minHeap is not empty
            while(!pq.isEmpty()){
                //extract the min
                Node extractedNode = pq.extractMin();

                //extracted vertex
                int extractedVertex = extractedNode.id;
                SPT[extractedVertex] = true;

                //iterate through all the adjacent vertices
                ArrayList<Edge> list = adjacencyList[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    int[] dest = edge.dest;
                    //only if  destination vertex is not present in SPT
                    if (SPT[dest[0]] == false) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey = nodes[extractedVertex].distance + edge.weight + dest[1];
                        int currentKey = nodes[dest[0]].distance;
                        if(currentKey > newKey){
                            //get the index which distance's needs a decrease;
                            int index = pq.indexes[dist[0]];

                            //get the node and update its value
                            Node node = pq.mH[index];
                            node.distance = newKey;
                            pq.bubbleUp(index);

                            nodes[dest[0]].distance = newKey;
                        }
                    }
                }
            }

            return pq.toArray();
        }
    
    }

    static class Node {

        public int id;
        public int weight;
        public int distance;

        public Node(int id, int weight, int distance) {
            this.id = id;
            this.weight = weight;
            this.distance = distance;
        }
    
    }

    static class Edge {

        public int[] src;
        public int[] dest;
        public int weight;

        public Edge(int src, int srcWeight, int dest, int destWeight, int weight) {
            this.src = new int[]{src, srcWeight};
            this.dest = new int[]{dest, destWeight};
            this.weight = weight;
        }
    
    }

}

























