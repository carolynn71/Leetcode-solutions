import java.util.*;

class Solution {

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<Edge>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int low = Integer.MAX_VALUE;
        int high = 0;

        // Build graph using only online nodes
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            if (!online[u] || !online[v]) {
                continue;
            }

            graph[u].add(new Edge(v, cost));
            indegree[v]++;

            low = Math.min(low, cost);
            high = Math.max(high, cost);
        }

        // No usable edges
        if (low == Integer.MAX_VALUE) {
            return -1;
        }

        // Topological ordering
        int[] topo = new int[n];
        int index = 0;

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {

            int u = queue.poll();
            topo[index++] = u;

            for (Edge e : graph[u]) {

                indegree[e.to]--;

                if (indegree[e.to] == 0) {
                    queue.offer(e.to);
                }
            }
        }

        // Binary search maximum possible minimum edge cost
        int answer = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canReach(graph, topo, mid, k)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    private boolean canReach(
            List<Edge>[] graph,
            int[] topo,
            int minCost,
            long k) {

        int n = graph.length;

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];

        Arrays.fill(dist, INF);

        dist[0] = 0;

        // DAG shortest path
        for (int u : topo) {

            if (dist[u] == INF) {
                continue;
            }

            if (dist[u] > k) {
                continue;
            }

            for (Edge e : graph[u]) {

                // Edge is too small for this candidate score
                if (e.cost < minCost) {
                    continue;
                }

                long newCost = dist[u] + e.cost;

                if (newCost < dist[e.to]) {
                    dist[e.to] = newCost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}