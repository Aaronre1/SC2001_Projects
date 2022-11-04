package SC2001.Project2;


public class Dijkstra {
    static final int INFINITY = 9999999;

    public static int[] ShortestPathList(Graph graph, int source) throws Exception {
        int d[] = new int[graph.V];
        int pi[] = new int[graph.V];
        int S[] = new int[graph.V];

        for (int i = 0; i < graph.V; i++) {
            d[i] = INFINITY;
            pi[i] = -1;
            S[i] = 0;
        }
        d[source] = 0;


        java.util.PriorityQueue<Graph.Edge> queue = new java.util.PriorityQueue<Graph.Edge>((v1,v2)-> v1.Weight - v2.Weight);
        //ArrayPQ queue = new ArrayPQ(graph.V);
        //MinHeap queue = new MinHeap(graph.V);
        for (int i = 0; i < graph.V; i++) {
            //queue.push(new Graph.Edge(i, d[i]));
            queue.add(new Graph.Edge(i, d[i]));
        }

        while (!queue.isEmpty()) {
            //var current = queue.pop();
            var current = queue.poll();
            int u = current.Vertex;
            S[u] = 1;

            for (var adj : graph.AdjList[current.Vertex]) {
                int v = adj.Vertex;
                if (S[v] != 1 && d[v] > d[u] + adj.Weight) {
                    d[v] = d[u] + adj.Weight;
                    pi[v] = u;

                    //queue.update(new Graph.Edge(adj.Vertex, d[v]));
                    queue.remove(adj);
                    queue.add(new Graph.Edge(adj.Vertex, d[v]));
                    //queue.push(new Graph.Edge(adj.Vertex, d[v]));
                }
            }
        }
        return d;
    }

    public static int[] ShortestPathMatrix(Graph graph, int source) throws Exception {
        int d[] = new int[graph.V];
        int pi[] = new int[graph.V];
        int S[] = new int[graph.V];

        for (int i = 0; i < graph.V; i++) {
            d[i] = INFINITY;
            pi[i] = -1;
            S[i] = 0;
        }
        d[source] = 0;

        //java.util.PriorityQueue<Graph.Edge> queue = new java.util.PriorityQueue<Graph.Edge>((v1,v2)-> v1.Weight - v2.Weight);
        ArrayPQ queue = new ArrayPQ(graph.V);
        for (int i = 0; i < graph.V; i++) {
            //queue.add(new Graph.Edge(i, d[i]));
            queue.push(new Graph.Edge(i, d[i]));

        }

        while (!queue.isEmpty()) {
            var current = queue.pop();
            //var current = queue.poll();
            int u = current.Vertex;
            S[u] = 1;

            for (int j = 0; j < graph.V; j++) {
                int v = j;
                int weight = graph.AdjMatrix[u][v];
                if (weight == 0) {
                    continue;
                }
                if (S[v] != 1 && d[v] > d[u] + weight) {
                    d[v] = d[u] + weight;
                    pi[v] = u;
                    queue.remove(new Graph.Edge(v, weight));
                    queue.push(new Graph.Edge(v, d[v]));
                    //queue.add(new Graph.Edge(v, d[v]));

                }
            }
        }
        return d;
    }

    public static void printDijkstra(int[] resultSet, int sourceVertex) {
        System.out.println("Dijkstra Algorithm: ");
        for (int i = 0; i < resultSet.length; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i +
                    " distance: " + resultSet[i]);
        }
    }
}
