import java.util.*;

class Solution {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {

        // Graph contains only the ORIGINAL nodes.
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph.
        // Edge cost = cnt + 1
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int cnt = edge[2];

            graph[u].add(new int[]{v, cnt + 1});
            graph[v].add(new int[]{u, cnt + 1});
        }

        // Dijkstra
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(
                        Comparator.comparingInt(a -> a[0])
                );

        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int d = current[0];
            int u = current[1];

            // Ignore outdated entry
            if (d > dist[u]) {
                continue;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int cost = edge[1];

                int newDist = d + cost;

                if (newDist < dist[v]) {

                    dist[v] = newDist;

                    pq.offer(new int[]{
                            newDist,
                            v
                    });
                }
            }
        }

        // Count reachable ORIGINAL nodes
        int answer = 0;

        for (int d : dist) {

            if (d <= maxMoves) {
                answer++;
            }
        }

        // Count reachable SUBDIVIDED nodes
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int cnt = edge[2];

            int fromU = 0;
            int fromV = 0;

            if (dist[u] <= maxMoves) {
                fromU = Math.min(
                        cnt,
                        maxMoves - dist[u]
                );
            }

            if (dist[v] <= maxMoves) {
                fromV = Math.min(
                        cnt,
                        maxMoves - dist[v]
                );
            }

            answer += Math.min(
                    cnt,
                    fromU + fromV
            );
        }

        return answer;
    }
}