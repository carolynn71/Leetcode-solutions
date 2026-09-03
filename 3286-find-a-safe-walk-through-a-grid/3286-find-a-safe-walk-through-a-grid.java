import java.util.*;

class Solution {

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        // dist[i][j] = minimum health loss needed
        // to reach (i, j)
        int[][] dist = new int[m][n];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Starting cell
        dist[0][0] = grid.get(0).get(0);

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                // Cost of going to next cell
                int newCost =
                    dist[r][c] + grid.get(nr).get(nc);

                // Found a cheaper way
                if (newCost < dist[nr][nc]) {

                    dist[nr][nc] = newCost;

                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // We need at least 1 health remaining
        return dist[m - 1][n - 1] < health;
    }
}