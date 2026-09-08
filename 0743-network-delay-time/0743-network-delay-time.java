import java.util.*;

class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {

        // Adjacency list
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph[u].add(new int[]{v, w});
        }

        // Shortest distances
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;

        // Min heap: {distance, node}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0])
            );

        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int currentDist = current[0];
            int node = current[1];

            // Ignore outdated heap entry
            if (currentDist > dist[node]) {
                continue;
            }

            // Relax all outgoing edges
            for (int[] edge : graph[node]) {

                int next = edge[0];
                int weight = edge[1];

                int newDist = currentDist + weight;

                if (newDist < dist[next]) {

                    dist[next] = newDist;

                    pq.offer(new int[]{
                        newDist,
                        next
                    });
                }
            }
        }

        // The signal reaches everyone
        // only when every node is reachable.
        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}