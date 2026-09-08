import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int INF = 1 << 29;

        int[][] dist = new int[n][n];

        // Initially, every pair is unreachable.
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Add edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            dist[u][v] = weight;
            dist[v][u] = weight;
        }

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }

        // Find city with minimum reachable neighbors.
        // Iterate from largest index to smallest so ties
        // automatically keep the largest city.
        int answer = -1;
        int minCount = Integer.MAX_VALUE;

        for (int city = n - 1; city >= 0; city--) {

            int count = 0;

            for (int other = 0; other < n; other++) {

                if (dist[city][other] <= distanceThreshold) {
                    count++;
                }
            }

            if (count < minCount) {
                minCount = count;
                answer = city;
            }
        }

        return answer;
    }
}