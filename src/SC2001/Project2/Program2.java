package SC2001.Project2;


import java.util.Random;

public class Program2 {
    public static void main(String[] args) throws Exception {
        //testDijkstra();
        testDijkstra();
        //Graph g2 = generateRandomUndirected(100,66);
        //Graph g2 = generateToyExample();
        //g2.print();

        //Dijkstra dijkstra = new Dijkstra();
        //System.out.println(Dijkstra.ShortestPathMatrix(g2, 0));


    }

    public static void testDijkstra() throws Exception {
        Dijkstra dijkstra = new Dijkstra();
//        Graph g = generateToyExample();
//        dijkstra.ShortestPathList(g,0);
        System.out.println("V, E, List(ns), Matrix(ns)");
        for (int i = 0; i < 1; i++) {
            int vertices = 100 + (i*100);
            Graph g2 = generateRandomUndirected(vertices, 0);
            //Graph g2 = generateToyExample();
            //System.out.println("V, E, List(ns), List(keyComp) , Matrix(ns), Matrix(keyComp)");

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


            for(int j = 0; j< vertices;j++){
                if (resultList[j] != resultMatrix[j]){

                    throw new Exception("peepeepoopoo wrong sequence " + resultList[j] + ":" + resultMatrix[j]);
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
        g.addEdge(0,new Graph.Edge(1,4)); //to node 1 with weight 4
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

    public static void testMinHeap() throws Exception {
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

        while (heap.size >0 ){
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
}
