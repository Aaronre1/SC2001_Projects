package SC2001.Project2;

import java.util.Random;

public class Program {
    public static void main(String[] args) throws Exception {
        testDijkstra();
        //testArrayPQ();
        //testMinHeap();
    }

    public static void testDijkstra() throws Exception {
        Dijkstra dijkstra = new Dijkstra();
//        Graph g = generateToyExample();
//        dijkstra.ShortestPathList(g,0);
        System.out.println("V, E, List(ns), Matrix(ns)");
        for (int i = 0; i < 300; i++) {
            int vertices = 10000;
            int extraEdges = 1000 + (i * 1000);
            Graph g2 = generateRandomUndirected(vertices, extraEdges);
            //g2.print();
            //Graph g2 = generateToyExample();
            //System.out.println("V, E, List(ns), List(keyComp) , Matrix(ns), Matrix(keyComp)");

            long start = System.currentTimeMillis();
            var resultList = dijkstra.ShortestPathList(g2, 0);
            long listNs = System.currentTimeMillis() - start;

            start = System.currentTimeMillis();
            var resultMatrix = dijkstra.ShortestPathMatrix(g2, 0);
            long matrixNs = System.currentTimeMillis() - start;

            System.out.println(g2.V + ", " + g2.E / 2 + ", " + listNs + ", " + matrixNs);
            //dijkstra.printDijkstra(resultList, 0);
            //System.out.println();
            //dijkstra.printDijkstra(resultMatrix, 0);
            //g2.print();
            //g2.printList();

//            for (int j = 0; j < vertices; j++) {
//                if (resultList[j] != resultMatrix[j] || resultList[j] == 9999999 || resultMatrix[j] == 9999999) {
//                    throw new Exception("peepeepoopoo wrong sequence " + resultList[j] + ":" + resultMatrix[j] + "@ " + j);
//                }
//            }

        }


    }

    private static Graph generateToyExample() {
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
        return g;
    }

    static Random rand = new Random();

    private static Graph generateRandomUndirected(int vertices, int extraEdges) throws Exception {
        Graph g = new Graph(vertices);

        for (int i = 1; i < vertices; i++) {

            int vert = 0;
            if (i!=0) {
                vert = rand.nextInt(0, i);
            }
            int weight = rand.nextInt(1, 10);
            g.addEdge(i, new Graph.Edge(vert, weight));
            g.addEdge(vert, new Graph.Edge(i, weight));
        }

        while(extraEdges > 0){
            int vert = rand.nextInt(0, vertices);
            int dest = vert;
            int weight = rand.nextInt(1, 10);
            while (dest == vert || g.AdjMatrix[vert][dest] != 0){
                dest = rand.nextInt(0, vertices);
            }
            g.addEdge(vert, new Graph.Edge(dest, weight));
            g.addEdge(dest, new Graph.Edge(vert, weight));
            extraEdges--;
        }
//        g.print();
//        for (int i = 0; i < vertices; i++) {
//            if (g.AdjMatrix[i][i] != 0) {
//                throw new Exception("invalid edge at " + i);
//            }
//        }

        return g;
    }

    public static void testMinHeap() throws Exception {
        var cap = 1000;
        var heap = new MinHeap(cap);

        for (int i = 0; i < cap; i++) {
            heap.push(new Graph.Edge(i, i * 10));
        }

        for (int i = 0; i < 100; i++) {
            var vert = new Graph.Edge(rand.nextInt(cap), 99);
            heap.remove(vert);
            //heap.push(vert);
        }
        int last = 0;
        while (!heap.isEmpty()) {
            var min = heap.pop();
            if (last > min.Weight) {
                throw new Exception(min.Vertex + " : " + min.Weight + "  last : " + last);
            }
            System.out.println(min.Vertex + " : " + min.Weight);
            last = min.Weight;
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
