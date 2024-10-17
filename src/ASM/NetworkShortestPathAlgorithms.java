package ASM;

public class NetworkShortestPathAlgorithms {

    private static final int MAX = 999999;

    // Method to find the vertex with minimum distance
    private int minDistance(int[] dist, boolean[] visited, int n) {
        int min = MAX, minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Dijkstra's algorithm
    public void dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n]; // Distance array
        boolean[] visited = new boolean[n]; // Visited vertices

        // Initialize distances and visited set
        for (int i = 0; i < n; i++) {
            dist[i] = MAX;
            visited[i] = false;
        }
        dist[src] = 0; // Source distance is 0

        // Find shortest path for all vertices
        for (int count = 0; count < n - 1; count++) {
            int m = minDistance(dist, visited, n);
            visited[m] = true;

            // Update distance of adjacent vertices
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[m][v] != 0 && dist[m] != MAX && dist[m] + graph[m][v] < dist[v]) {
                    dist[v] = dist[m] + graph[m][v];
                }
            }
        }

        // Display the shortest distance from source
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    // Prim's Algorithm for MST
    public void primJarnik(int[][] graph) {
        int V = graph.length;
        int[] parent = new int[V]; // Stores the MST
        int[] key = new int[V];    // Minimum key value to pick an edge
        boolean[] mstSet = new boolean[V]; // Tracks vertices included in MST

        // Initialize key values and MST set
        for (int i = 0; i < V; i++) {
            key[i] = MAX;
            mstSet[i] = false;
        }

        // Starting vertex (can be any, here 0)
        key[0] = 0;
        parent[0] = -1; // First node is always the root of MST

        // MST construction loop
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet in MST
            int u = minDistance(key, mstSet, V);
            mstSet[u] = true; // Add the picked vertex to the MST set

            // Update key values of adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non-zero for adjacent vertices,
                // mstSet[v] is false for vertices not yet in MST
                // Update the key only if graph[u][v] is smaller than the current key
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the MST
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static void main(String[] args) {
        NetworkShortestPathAlgorithms algorithms = new NetworkShortestPathAlgorithms();
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        System.out.println("Dijkstra's Algorithm:");
        algorithms.dijkstra(graph, 3);

        System.out.println("\nPrim-Jarnik Algorithm (Minimum Spanning Tree):");
        algorithms.primJarnik(graph);
    }
}
