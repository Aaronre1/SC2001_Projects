package SC2001.Project2b;

import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    int V;
    int E;

    public LinkedList<Edge>[] getAdjList() {
        return AdjList;
    }

    public void setAdjList(LinkedList<Edge>[] adjList) {
        AdjList = adjList;
    }

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
        Program.Graph2 graph2 = new Program.Graph2(V);
        //System.out.print("V > " +V+ " \n ");
        for(int i=0; i<V; i++){
            //System.out.print("i > " +i+ " \n ");
            //System.out.print(" list >> "+ AdjList[i] + " \n ");
            for(int x =0; x<AdjList[i].size();x++){
                graph2.addEdge2(i,AdjList[i].get(x).getVertex(),AdjList[i].get(x).getWeight());
                //System.out.print("x > " + x + " \n ");
              //System.out.print("v > " +AdjList[i].get(x).getVertex() + " \n ");
               //System.out.print("w > " + AdjList[i].get(x).getWeight() + " \n ");
            }
            //System.out.print("asd > "+AdjList[i].get(i).toString() + "  ");
            //graph2.addEdge(i,);
            for (int j=0; j<V; j++){
                //System.out.print(AdjMatrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("Calling graph 2---\n");
        graph2.dijkstra_GetMinDistances(0);
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
        public int getVertex() {
            return Vertex;
        }

        public void setVertex(int vertex) {
            Vertex = vertex;
        }

        int Vertex;

        public int getWeight() {
            return Weight;
        }

        public void setWeight(int weight) {
            Weight = weight;
        }

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
