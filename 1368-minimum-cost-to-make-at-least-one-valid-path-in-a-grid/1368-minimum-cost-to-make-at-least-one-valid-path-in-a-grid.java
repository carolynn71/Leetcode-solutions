import java.util.*;

class Solution {

    public int minCost(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Directions:
        // 1 = right
        // 2 = left
        // 3 = down
        // 4 = up
        int[] dr = {0, 0, 0, 1, -1};
        int[] dc = {0, 1, -1, 0, 0};

        // Minimum cost to reach each cell
        int[][] dist = new int[m][n];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Deque for 0-1 BFS
        Deque<int[]> deque = new ArrayDeque<>();

        dist[0][0] = 0;
        deque.offerFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {

            int[] current = deque.pollFirst();

            int r = current[0];
            int c = current[1];

            if (r == m - 1 && c == n - 1) {
                return dist[r][c];
            }

            // Try all 4 directions
            for (int dir = 1; dir <= 4; dir++) {

                int nr = r + dr[dir];
                int nc = c + dc[dir];

                // Outside grid?
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Following the existing arrow costs 0.
                // Changing it costs 1.
                int cost = (grid[r][c] == dir) ? 0 : 1;

                int newCost = dist[r][c] + cost;

                // Found a cheaper way to reach this cell
                if (newCost < dist[nr][nc]) {

                    dist[nr][nc] = newCost;

                    if (cost == 0) {
                        // Process immediately
                        deque.offerFirst(new int[]{nr, nc});
                    } else {
                        // Process later
                        deque.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return -1;
    }
}