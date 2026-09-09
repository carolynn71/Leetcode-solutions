import java.util.*;

class Solution {
    public int minimumObstacles(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] dist = new int[m][n];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // {row, column}
        Deque<int[]> deque = new ArrayDeque<>();

        dist[0][0] = 0;
        deque.offerFirst(new int[]{0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!deque.isEmpty()) {

            int[] current = deque.pollFirst();

            int r = current[0];
            int c = current[1];

            // Try all 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside the grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Entering this cell:
                // 0 -> cost 0
                // 1 -> cost 1
                int cost = grid[nr][nc];

                int newDist = dist[r][c] + cost;

                if (newDist < dist[nr][nc]) {

                    dist[nr][nc] = newDist;

                    if (cost == 0) {
                        deque.offerFirst(new int[]{nr, nc});
                    } else {
                        deque.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }
}