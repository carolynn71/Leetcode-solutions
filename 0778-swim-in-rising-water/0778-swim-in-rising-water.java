import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {

        int n = grid.length;

        // Min heap:
        // {maximum elevation needed so far, row, column}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        boolean[][] visited = new boolean[n][n];

        // Start from (0, 0)
        pq.offer(new int[]{grid[0][0], 0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int time = current[0];
            int r = current[1];
            int c = current[2];

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;

            // Reached destination
            if (r == n - 1 && c == n - 1) {
                return time;
            }

            // Explore 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                int newTime =
                    Math.max(time, grid[nr][nc]);

                pq.offer(new int[]{
                    newTime,
                    nr,
                    nc
                });
            }
        }

        return -1;
    }
}