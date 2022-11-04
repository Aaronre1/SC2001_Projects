package SC2001.Project2;

import java.util.LinkedList;

public class Graph {
    int V;
    int E;
    LinkedList<Edge> AdjList[];
    int AdjMatrix[][];

    public Graph(int vertices){
        V = vertices;
        E = 0;
        AdjList = new LinkedList[vertices];
        AdjMatrix = new int[vertices][vertices];
        for(int i=0; i<V; i++){
            AdjList[i] = new LinkedList<>();
            for (int j=0; j<V; j++){
                AdjMatrix[i][j] = 0;
            }
        }
    }
    public void print(){
        for(int i=0; i<V; i++){
            for (int j=0; j<V; j++){
                System.out.print(AdjMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public void printList(){
        for(int i=0; i<V; i++){
            System.out.print(i);
            for (var e: AdjList[i]){
                System.out.print( " -" + e.Weight + "-> " + e.Vertex);
            }
            System.out.println();
        }
    }

    public void addEdge(int vertex, Edge dest){
        E++;
        AdjList[vertex].addFirst(dest);
        AdjMatrix[vertex][dest.Vertex] = dest.Weight;
    }

    static class Edge {
        int Vertex;
        int Weight;
        Edge(int vertex, int weight){
            Vertex = vertex;
            Weight = weight;
        }
        @Override
        public boolean equals(Object o){
            Edge edge = (Edge) o;
            return Vertex == edge.Vertex;
        }
    }

}
