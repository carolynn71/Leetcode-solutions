import java.util.*;

class Solution {

    static final long MOD = 1_000_000_007L;
    static final int LOG = 17;

    List<Integer>[] graph;
    int[][] up;
    int[] depth;
    long[] pow2;

    // DFS to calculate depth and ancestors
    private void dfs(int node, int parent) {

        up[0][node] = parent;

        for (int j = 1; j < LOG; j++) {
            up[j][node] = up[j - 1][up[j - 1][node]];
        }

        for (int next : graph[node]) {

            if (next == parent) {
                continue;
            }

            depth[next] = depth[node] + 1;

            dfs(next, node);
        }
    }

    // Find LCA of u and v
    private int lca(int u, int v) {

        // Make u the deeper node
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring u to same depth as v
        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {

            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        // Move both upward
        for (int j = LOG - 1; j >= 0; j--) {

            if (up[j][u] != up[j][v]) {

                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    public int[] assignEdgeWeights(
            int[][] edges,
            int[][] queries) {

        int n = edges.length + 1;

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        depth = new int[n + 1];

        up = new int[LOG][n + 1];

        /*
         * Root tree at node 1.
         */
        dfs(1, 1);

        /*
         * Precompute powers of 2.
         */
        pow2 = new long[n + 1];

        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int ancestor = lca(u, v);

            int distance =
                    depth[u]
                    + depth[v]
                    - 2 * depth[ancestor];

            /*
             * No edges in the path.
             */
            if (distance == 0) {
                answer[i] = 0;
            } else {
                answer[i] =
                    (int) pow2[distance - 1];
            }
        }

        return answer;
    }
}