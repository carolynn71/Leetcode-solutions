import java.util.*;

class Solution {

    private static final int MOD = 1_000_000_007;

    public int countRestrictedPaths(int n, int[][] edges) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        // ----------------------------------
        // 1. Dijkstra from node n
        // ----------------------------------

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[n] = 0;

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(
                        Comparator.comparingLong(a -> a[0])
                );

        pq.offer(new long[]{0, n});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            long currentDist = current[0];
            int u = (int) current[1];

            if (currentDist > dist[u]) {
                continue;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int weight = edge[1];

                long newDist = currentDist + weight;

                if (newDist < dist[v]) {

                    dist[v] = newDist;

                    pq.offer(new long[]{
                            newDist,
                            v
                    });
                }
            }
        }

        // ----------------------------------
        // 2. DP
        // ----------------------------------

        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i + 1;
        }

        // Smaller distance first
        Arrays.sort(order, Comparator.comparingLong(x -> dist[x]));

        long[] ways = new long[n + 1];

        ways[n] = 1;

        for (int u : order) {

            if (u == n) {
                continue;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];

                if (dist[u] > dist[v]) {
                    ways[u] += ways[v];

                    if (ways[u] >= MOD) {
                        ways[u] -= MOD;
                    }
                }
            }
        }

        return (int) ways[1];
    }
}