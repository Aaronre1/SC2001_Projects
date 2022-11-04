package SC2001.Project2b;

import java.util.Arrays;

public class Dijkstra {
    static final int INFINITY = Integer.MAX_VALUE;

    public static int[] ShortestPathList(Graph graph, int source) {
        int d[] = new int[graph.V]; //array of estimated shortest path
        int pi[] = new int[graph.V]; //predecessors for each vertex
        int S[] = new int[graph.V]; //empty

        for (int i = 0; i < graph.V; i++) {
            d[i] = INFINITY;
            pi[i] = -1;
            S[i] = 0;
        }
        d[source] = 0;

        MinHeap queue = new MinHeap(graph.V);
        for (int i = 0; i < graph.V; i++) {
            queue.push(new Graph.Edge(i, d[i]));
        }

        while (queue.size > 0) {
            var current = queue.pop();
            int u = current.Vertex;
            S[u] = 1;

            for (var adj : graph.AdjList[current.Vertex]) {
                int v = adj.Vertex;
                //System.out.println("v > "+v);
                if (S[v] != 1 && d[v] > d[u] + adj.Weight) {
                    d[v] = d[u] + adj.Weight;
                    //System.out.println("-------");
                    //System.out.println("d[v] > "+d[v]);
                    //System.out.println("d[u] > "+d[u]);
                    //System.out.println("adj.Weight> "+adj.Weight);
                    pi[v] = u;
                    //System.out.println("adj> "+adj);
                    queue.remove(adj);
                   // System.out.println("adj.Vertex> "+adj.Vertex);
                    queue.push(new Graph.Edge(adj.Vertex, d[v]));
                }
            }
        }

        System.out.println("list d >>> " +Arrays.toString(d));
        return d;
    }

    public static int[] ShortestPathMatrix(Graph graph, int source) {
        int d[] = new int[graph.V];
        int pi[] = new int[graph.V];
        int S[] = new int[graph.V];

        for (int i = 0; i < graph.V; i++) {
            d[i] = INFINITY;
            pi[i] = -1;
            S[i] = 0;
        }
        d[source] = 0;

        ArrayPQ queue = new ArrayPQ(graph.V);
        for (int i = 0; i < graph.V; i++) {
            queue.push(new Graph.Edge(i, d[i]));
        }

        while (!queue.isEmpty()) {
            var current = queue.pop();
            int u = current.Vertex;
            S[u] = 1;

            for (int j = 0; j < graph.V; j++) {
                int v = j;
                int weight = graph.AdjMatrix[u][v];
                if (weight == 0){
                    continue;
                }
                if (S[v] != 1 && d[v] > d[u] + weight) {
                    d[v] = d[u] + weight;
                    pi[v] = u;
                    //System.out.println("Remove, Push" + );
                    queue.remove(new Graph.Edge(v, weight));
                    queue.push(new Graph.Edge(v, d[v]));
                }
            }

//            for (var adj : graph.AdjList[current.Vertex]) {
//                int v = adj.Vertex;
//                if (S[v] != 1 && d[v] > d[u] + adj.Weight) {
//                    d[v] = d[u] + adj.Weight;
//                    pi[v] = u;
//                    queue.remove(adj);
//                    queue.push(new Graph.Edge(adj.Vertex, d[v]));
//                }
//            }
        }
        System.out.println("matrix d >> "+ Arrays.toString(d));
        return d;
    }

    public static void printDijkstra(int[] resultSet, int sourceVertex) {
        System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");
        for (int i = 0; i < resultSet.length; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i +
                    " distance: " + resultSet[i]);
        }
    }
}
