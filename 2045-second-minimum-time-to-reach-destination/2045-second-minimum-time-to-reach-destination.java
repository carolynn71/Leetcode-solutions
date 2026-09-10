import java.util.*;

class Solution {

    public int secondMinimum(int n, int[][] edges, int time, int change) {

        // Build graph
        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        // dist[i][0] = shortest number of edges
        // dist[i][1] = second-shortest number of edges
        int[][] dist = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            dist[i][0] = Integer.MAX_VALUE;
            dist[i][1] = Integer.MAX_VALUE;
        }

        Queue<int[]> queue = new ArrayDeque<>();

        dist[1][0] = 0;
        queue.offer(new int[]{1, 0});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int u = current[0];
            int d = current[1];

            for (int v : graph[u]) {

                int newDist = d + 1;

                // New shortest path
                if (newDist < dist[v][0]) {

                    dist[v][1] = dist[v][0];
                    dist[v][0] = newDist;

                    queue.offer(new int[]{v, newDist});
                }

                // New second-shortest path
                else if (dist[v][0] < newDist
                        && newDist < dist[v][1]) {

                    dist[v][1] = newDist;

                    queue.offer(new int[]{v, newDist});
                }
            }
        }

        // Convert second-shortest edge count
        // into actual travel time.
        int answer = 0;

        for (int i = 0; i < dist[n][1]; i++) {

            // Travel one edge
            answer += time;

            // Before the next edge, check the signal.
            if (i < dist[n][1] - 1) {

                int cycle = answer / change;

                // Odd cycle = RED
                if (cycle % 2 == 1) {

                    answer = (cycle + 1) * change;
                }
            }
        }

        return answer;
    }
}