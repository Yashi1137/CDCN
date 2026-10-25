import java.util.*;
public class Dijkstra {
    static class Pair{
        int node;
        int distance;
        Pair(int node, int distance){
            this.node=node;
            this.distance=distance;
        }
    }
    static int[] dijkstra(int v, ArrayList<ArrayList<Pair>> graph, int source){
        int[] dist=new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source]=0;
        PriorityQueue<Pair> pq=new PriorityQueue<>(
            (a,b)->a.distance-b.distance
        );
        pq.add(new Pair(source,0));
        while(!pq.isEmpty()){
            Pair current= pq.poll();
            int node=current.node;
            int distance=current.distance;
            if(distance>dist[node]){
                continue;
            }
            //visit all neighbours
            for(Pair neighbour: graph.get(node)) {
                int nextNode=neighbour.node;
                int edgeWeight=neighbour.distance;
                int newDistance=distance+edgeWeight;
                if(newDistance<dist[nextNode]){
                    dist[nextNode]=newDistance;
                    pq.add(new Pair(nextNode,newDistance));
                }
            }
        }
        return dist;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt(); // Number of vertices
        int e = sc.nextInt(); // Number of edges

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        // Create an empty list for every vertex
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        // Read all edges
        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            graph.get(from).add(new Pair(to, weight));
        }

        int source = sc.nextInt();

        int[] result = dijkstra(v, graph, source);

        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("To " + i + " = " + result[i]);
        }
    }
}
