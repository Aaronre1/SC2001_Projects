package SC2001.Project2a;

import java.util.Arrays;
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

    public void addEdge(int vertex, Edge dest){
        E++;
        AdjList[vertex].addFirst(dest);
        AdjMatrix[vertex][dest.Vertex] = dest.Weight;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "V=" + V +
                ", E=" + E +
                ", AdjList=" + Arrays.toString(AdjList) +
                ", AdjMatrix=" + Arrays.toString(AdjMatrix) +
                '}';
    }

    static class Edge {
        int Vertex;
        int Weight;
        Edge(int vertex, int weight){
            Vertex = vertex;
            Weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "Vertex=" + Vertex +
                    ", Weight=" + Weight +
                    '}';
        }
    }

}
