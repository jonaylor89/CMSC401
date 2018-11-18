
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Stream;

class Assignment3 {
static HashMap<Integer, Integer> motelPrices = new HashMap<Integer, Integer>(); 
    public static void main(String[] argv) {

        Scanner in = new Scanner(System.in);

        int numOfCities = in.nextInt();
        in.nextLine();

        int numOfHighways = in.nextInt();
        in.nextLine();

        
        for (int i = 0; i < numOfCities; i++) {
            motelPrices.put(i, 0);
        }

        motelPrices.put(0, 0);
        motelPrices.put(1, 0);

        for (int i = 3; i <= numOfCities; i++) {
            int cityNumber = in.nextInt();

            int motelPrice = in.nextInt();
            in.nextLine();

            motelPrices.put(cityNumber-1, motelPrice);
        }

        Graph g = new Graph(numOfCities);


        for (int i = 0; i < numOfHighways; i++) {
            int cityOne = in.nextInt();
            int cityTwo = in.nextInt();
            int price = in.nextInt();
            in.nextLine();

            g.addEdge(cityOne-1, motelPrices.get(cityOne-1), cityTwo-1, motelPrices.get(cityTwo-1), price);
        }

        // Compute shortest path
        Node[] distances = g.dijkstra(0);

        // Print result
        System.out.println(distances[1].distance);

        in.close();
    }

    static class PriorityQueue {
        int capacity;
        int currentSize;
        Node[] mH;
        int [] indexes; //will be used to decrease the distance


        public PriorityQueue(int capacity) {
            this.capacity = capacity;
            mH = new Node[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new Node();
            mH[0].distance = Integer.MIN_VALUE;
            mH[0].vertex=-1;
            currentSize = 0;
        }

        public void insert(Node x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos/2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance) {
                Node currentNode = mH[currentIdx];
                Node parentNode = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx,parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx/2;
            }
        }

        public Node extractMin() {
            Node min = mH[1];
            Node lastNode = mH[currentSize];
//            update the indexes[] and move the last node to the top
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k+1;
            if (leftChildIdx < queueSize() && mH[smallest].distance > mH[leftChildIdx].distance) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < queueSize() && mH[smallest].distance > mH[rightChildIdx].distance) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                Node smallestNode = mH[smallest];
                Node kNode = mH[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            Node temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int queueSize(){
            return currentSize;
        }
    }

    static class Edge {
        int src;
        int srcWeight;
        int dest;
        int destWeight;
        int weight;

        public Edge(int src, int srcWeight, int dest, int destWeight, int weight) {
            this.src = src;
            this.srcWeight = srcWeight;
            this.dest = dest;
            this.destWeight = destWeight;
            this.weight = weight;
        }
    }

    static class Node{
        int vertex;
        int distance;
    }

    static class Graph {
        int vertices;
        ArrayList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new ArrayList[vertices];

            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new ArrayList<Edge>();
            }
        }

        public void addEdge(int src, int srcWeight, int dest, int destWeight, int weight) {
            Edge edge = new Edge(src, srcWeight, dest, destWeight, weight);
            adjacencylist[src].add(edge);

            Edge edge2 = new Edge(dest, destWeight, src, srcWeight, weight);
            adjacencylist[dest].add(edge2);

        }

        public Node[] dijkstra(int sourceVertex){
            int INFINITY = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[vertices];

//          //create heapNode for all the vertices
            Node [] nodes = new Node[vertices];
            for (int i = 0; i < vertices ; i++) {
                nodes[i] = new Node();
                nodes[i].vertex = i;
                nodes[i].distance = INFINITY;
            }

            //decrease the distance for the first index
            nodes[sourceVertex].distance = 0;

            //add all the vertices to the PriorityQueue
            PriorityQueue pq = new PriorityQueue(vertices);
            for (int i = 0; i < vertices ; i++) {
                pq.insert(nodes[i]);
            }

            while(!pq.isEmpty()){
                Node extractedNode = pq.extractMin();

                int extractedVertex = extractedNode.vertex;
                SPT[extractedVertex] = true;

                //iterate through all the adjacent vertices
                ArrayList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size() ; i++) {
                    Edge edge = list.get(i);
                    int dest = edge.dest;
                    //only if  dest vertex is not present in SPT
                    if(SPT[dest] == false) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey =  nodes[extractedVertex].distance + edge.weight + edge.destWeight;
                        int currentKey = nodes[dest].distance;
                        if (currentKey > newKey){
                            decreaseKey(pq, newKey, dest);
                            nodes[dest].distance = newKey;
                        }
                    }
                }

            }

            return nodes;
        }

        public void decreaseKey(PriorityQueue pq, int newKey, int vertex){

            //get the index which distance's needs a decrease;
            int index = pq.indexes[vertex];

            //get the node and update its value
            Node node = pq.mH[index];
            node.distance = newKey;
            pq.bubbleUp(index);
        }

    }

}
























