import java.util.*;

class Solution {

    public double maxProbability(
            int n,
            int[][] edges,
            double[] succProb,
            int start_node,
            int end_node) {

        // Build adjacency list
        List<double[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {

            int u = edges[i][0];
            int v = edges[i][1];
            double probability = succProb[i];

            graph[u].add(new double[]{v, probability});
            graph[v].add(new double[]{u, probability});
        }

        // best[i] = maximum probability to reach i
        double[] best = new double[n];
        best[start_node] = 1.0;

        // MAX-HEAP
        // {probability, node}
        PriorityQueue<double[]> pq =
                new PriorityQueue<>(
                        (a, b) -> Double.compare(b[0], a[0])
                );

        pq.offer(new double[]{1.0, start_node});

        while (!pq.isEmpty()) {

            double[] current = pq.poll();

            double probability = current[0];
            int node = (int) current[1];

            // Ignore outdated entry
            if (probability < best[node]) {
                continue;
            }

            // We reached destination with the best probability
            if (node == end_node) {
                return probability;
            }

            for (double[] edge : graph[node]) {

                int next = (int) edge[0];
                double edgeProbability = edge[1];

                double newProbability =
                        probability * edgeProbability;

                if (newProbability > best[next]) {

                    best[next] = newProbability;

                    pq.offer(new double[]{
                            newProbability,
                            next
                    });
                }
            }
        }

        return 0.0;
    }
}