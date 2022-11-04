package SC2001.Project2b;

import java.util.*;

public class Program {
    public static void main(String[] args) throws Exception {
        testDijkstra();
        //MinHeap mh = new MinHeap(20);
        //mh.print();
    }

    public static void testDijkstra() throws Exception {
        Dijkstra dijkstra = new Dijkstra();
//        Graph g = generateToyExample();
 //       dijkstra.ShortestPathList(g,0);
        System.out.println("V, E, List(ns), Matrix(ns)");
        for (int i = 0; i < 10; i++) {
            int vertices = 100 + (i*1000);
            Graph g2 = generateRandomUndirected(vertices, 0);
            //Graph g2 = generateToyExample();
            //System.out.println("V, E, List(ns), List(keyComp) , Matrix(ns), Matrix(keyComp)");
            g2.print();
            long start = System.nanoTime();
            var resultList = dijkstra.ShortestPathList(g2, 0);
            long listNs = System.nanoTime() - start;
            start = System.nanoTime();
            var resultMatrix = dijkstra.ShortestPathMatrix(g2, 0);
            long matrixNs = System.nanoTime() - start;

            System.out.println(g2.V + ", " + g2.E / 2 + ", " + listNs + ", " + matrixNs);
            dijkstra.printDijkstra(resultList,0);
            System.out.println();
            dijkstra.printDijkstra(resultMatrix,0);

            for (int j = 0; j < vertices; j++) {
                if (resultList[j] != resultMatrix[j] || resultList[j] == 9999999 || resultMatrix[j] == 9999999) {
                    throw new Exception("peepeepoopoo wrong sequence " + resultList[j] + ":" + resultMatrix[j] + "@ " + j);
                }
            }
        }


    }

    private static Graph generateToyExample() {
        /*
        Graph g = new Graph(5);
        g.addEdge(0, new Graph.Edge(1, 10));
        g.addEdge(0, new Graph.Edge(2, 5));
        g.addEdge(1, new Graph.Edge(3, 1));
        g.addEdge(1, new Graph.Edge(2, 2));
        g.addEdge(2, new Graph.Edge(1, 3));
        g.addEdge(2, new Graph.Edge(3, 9));
        g.addEdge(2, new Graph.Edge(4, 2));
        g.addEdge(3, new Graph.Edge(4, 4));
        g.addEdge(4, new Graph.Edge(3, 6));
        g.addEdge(4, new Graph.Edge(0, 7));
        */
        /*
        Graph g = new Graph(6);
        g.addEdge(0, new Graph.Edge(1, 4));
        g.addEdge(0, new Graph.Edge(2, 3));
        g.addEdge(1, new Graph.Edge(2, 1));
        g.addEdge(1, new Graph.Edge(3, 2));
        g.addEdge(2, new Graph.Edge(3, 4));
        g.addEdge(3, new Graph.Edge(4, 2));
        g.addEdge(4, new Graph.Edge(5, 6));
        */
        Graph g = new Graph(9);
        g.addEdge(0,new Graph.Edge(1,4));
        g.addEdge(0,new Graph.Edge(7,8));
        g.addEdge(1,new Graph.Edge(2,8));
        g.addEdge(2,new Graph.Edge(5,4));
        g.addEdge(3,new Graph.Edge(2,7));
        g.addEdge(3,new Graph.Edge(4,9));
        g.addEdge(1,new Graph.Edge(7,11));
        g.addEdge(1,new Graph.Edge(0,7));
        g.addEdge(2,new Graph.Edge(1,8));
        g.addEdge(2,new Graph.Edge(3,7));
        g.addEdge(7,new Graph.Edge(1,11));
        g.addEdge(7,new Graph.Edge(6,1));
        g.addEdge(7,new Graph.Edge(8,7));
        g.addEdge(8,new Graph.Edge(2,2));
        g.addEdge(2,new Graph.Edge(8,2));

        g.addEdge(3,new Graph.Edge(5,14));
        g.addEdge(4,new Graph.Edge(3,9));
        g.addEdge(4,new Graph.Edge(5,10));
        g.addEdge(5,new Graph.Edge(4,10));
        g.addEdge(5,new Graph.Edge(6,2));
        g.addEdge(6,new Graph.Edge(5,2));
        g.addEdge(6,new Graph.Edge(7,1));
        g.addEdge(6,new Graph.Edge(8,6));
        g.addEdge(7,new Graph.Edge(0,8));
        g.addEdge(8,new Graph.Edge(6,6));


        return g;
    }

    static Random rand = new Random();

    private static Graph generateRandomUndirected(int vertices, int extraEdges) throws Exception {
        Graph g = new Graph(vertices);
        for (int i = 0; i < vertices; i++) {
            int edges = 1;
            if (extraEdges != 0) {
                edges = rand.nextInt(1, 4);
                extraEdges -= edges;
            }
            if (i == 0) {
                continue;
            }
            // j = #edges
            for (int j = 0; j < edges; j++) {
                int vert =rand.nextInt(0,i);
//                while (i == rand.nextInt(vertices)){
//                    vert = rand.nextInt(vertices);
//                }
                int weight = rand.nextInt(1,9);
                if (j == 0) {
                    //link back to one of previous vertices
                    vert = rand.nextInt(0,i);
                    //vert = i-1;
                }

                g.addEdge(i, new Graph.Edge(vert, weight));
                //System.out.println("vert >> " +vert);
                //System.out.println("weight >>> "+weight);
                //System.out.println("i >>> "+i);
                g.addEdge(vert, new Graph.Edge(i, weight));
            }
        }
        //g.print();
        for (int i = 0; i < vertices; i++) {
            if (g.AdjMatrix[i][i] != 0){
                throw new Exception("invalid edge at " + i);
            }
        }

        return g;
    }

    public static void testMinHeap() {
        var heap = new MinHeap(10);

        heap.push(new Graph.Edge(1, 10));
        heap.push(new Graph.Edge(2, 9));
        heap.push(new Graph.Edge(3, 8));
        heap.push(new Graph.Edge(4, 7));
        heap.push(new Graph.Edge(5, 11));
        heap.push(new Graph.Edge(6, 12));
        heap.push(new Graph.Edge(7, 1));
        heap.push(new Graph.Edge(8, 2));
        heap.push(new Graph.Edge(9, 3));
        heap.push(new Graph.Edge(10, 43));

        heap.print();

        System.out.println("=======");

        heap.remove(new Graph.Edge(5, 11));
        heap.print();
        System.out.println("=======");

        for (int i = 0; i < 10; i++) {
            var min = heap.pop();
            System.out.println(min.Vertex + " : " + min.Weight);
        }
    }

    public static void testArrayPQ() {
        var array = new ArrayPQ(10);

        array.push(new Graph.Edge(1, 10));
        array.push(new Graph.Edge(2, 9));
        array.push(new Graph.Edge(3, 8));
        array.push(new Graph.Edge(4, 7));
        array.push(new Graph.Edge(5, 11));
        array.push(new Graph.Edge(6, 12));
        array.push(new Graph.Edge(7, 1));
        array.push(new Graph.Edge(8, 2));
        array.push(new Graph.Edge(9, 3));
        array.push(new Graph.Edge(10, 43));

        array.print();
        System.out.println("=======");

        array.remove(new Graph.Edge(5, 11));
        array.print();
        System.out.println("=======");

        while (!array.isEmpty()) {
            var min = array.pop();
            System.out.println(min.Vertex + " : " + min.Weight);
        }

    }
//----------------------------------------
static class Edge2 {
    int source;
    int destination;
    int weight;

    public Edge2(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge2 [source=" + source + ", destination=" + destination + ", weight=" + weight + "]";
    }
}

    static class HeapNode2{
        int vertex;
        int distance;
        @Override
        public String toString() {
            return "HeapNode2 [vertex=" + vertex + ", distance=" + distance + "]";
        }
    }
    static class Graph2 {
        int vertices;
        LinkedList<Edge2>[] adjacencylist;

        Graph2(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge2(int source, int destination, int weight) {
            Edge2 edge = new Edge2(source, destination, weight);
            adjacencylist[source].addFirst(edge);

            edge = new Edge2(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph
        }

        public HeapNode2[] dijkstra_GetMinDistances(int sourceVertex){
            int INFINITY = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[vertices]; //shortest path tree

            //create heapNode for all the vertices
            HeapNode2 [] heapNodes = new HeapNode2[vertices];
            for (int i = 0; i <vertices ; i++) {
                heapNodes[i] = new HeapNode2();
                heapNodes[i].vertex = i;
                heapNodes[i].distance = INFINITY;
            }

            //decrease the distance for the first index
            heapNodes[sourceVertex].distance = 0;

            //add all the vertices to the MinHeap2
            MinHeap2 minHeap = new MinHeap2(vertices);
            for (int i = 0; i <vertices ; i++) {
                //System.out.println(heapNodes[i]);
                minHeap.insert(heapNodes[i]);
            }
            //while minHeap is not empty
            while(!minHeap.isEmpty()){
                //extract the min
                HeapNode2 extractedNode = minHeap.extractMin();

                //extracted vertex aka sort the array
                int extractedVertex = extractedNode.vertex;
                SPT[extractedVertex] = true;

                //iterate through all the adjacent vertices
                LinkedList<Edge2> list = adjacencylist[extractedVertex];
                for (int i = 0; i <list.size() ; i++) {
                    Edge2 edge = list.get(i);
                    int destination = edge.destination;
                    //only if  destination vertex is not present in SPT
                    if(SPT[destination]==false ) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey =  heapNodes[extractedVertex].distance + edge.weight ;
                        int currentKey = heapNodes[destination].distance;
                        if(currentKey>newKey){
                            decreaseKey(minHeap, newKey, destination);
                            heapNodes[destination].distance = newKey;
                        }
                    }
                }
            }
            //print SPT
            printDijkstra2(heapNodes, sourceVertex);
            return heapNodes;
        }

        public void decreaseKey(MinHeap2 minHeap, int newKey, int vertex){

            //get the index which distance's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            HeapNode2 node = minHeap.mH[index];
            node.distance = newKey;
            minHeap.bubbleUp(index);
        }

        public void printDijkstra2(HeapNode2[] resultSet, int sourceVertex){
            System.out.println("n-> Dijkstra Algorithm: (Adjacency List + Min Heap)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                        " distance: " + resultSet[i].distance);
            }
        }

        @Override
        public String toString() {
            return "Graph2 [vertices=" + vertices + ", adjacencylist=" + Arrays.toString(adjacencylist) + "]";
        }
    }
    static class MinHeap2{
        int capacity;
        int size;
        HeapNode2[] mH;
        int [] indexes; //will be used to decrease the distance


        public MinHeap2(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode2[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode2();
            mH[0].distance = Integer.MIN_VALUE;
            mH[0].vertex=-1;
            size = 0;
        }

        public void display() {
            for (int i = 0; i <=size; i++) {
                System.out.println(" " + mH[i].vertex + "   distance   " + mH[i].distance);
            }
            System.out.println("________________________");
        }

        public void insert(HeapNode2 x) {
            size++;
            int idx = size;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos/2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance) {
                HeapNode2 currentNode = mH[currentIdx];
                HeapNode2 parentNode = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx,parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx/2;
            }
        }

        public HeapNode2 extractMin() {
            HeapNode2 min = mH[1];
            HeapNode2 lastNode = mH[size];
//            update the indexes[] and move the last node to the top
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[size] = null;
            sinkDown(1);
            size--;
            return min;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k+1;
            if (leftChildIdx < heapSize() && mH[smallest].distance > mH[leftChildIdx].distance) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest].distance > mH[rightChildIdx].distance) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                HeapNode2 smallestNode = mH[smallest];
                HeapNode2 kNode = mH[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            HeapNode2 temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int heapSize(){
            return size;
        }
    }
}
