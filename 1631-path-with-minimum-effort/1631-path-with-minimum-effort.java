import java.util.*;

class Solution {
    public int minimumEffortPath(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dist = new int[rows][cols];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // {effort, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        dist[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int effort = current[0];
            int r = current[1];
            int c = current[2];

            if (effort > dist[r][c]) {
                continue;
            }

            if (r == rows - 1 && c == cols - 1) {
                return effort;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
                    continue;
                }

                int heightDifference =
                    Math.abs(heights[r][c] - heights[nr][nc]);

                int newEffort =
                    Math.max(effort, heightDifference);

                if (newEffort < dist[nr][nc]) {

                    dist[nr][nc] = newEffort;

                    pq.offer(new int[]{
                        newEffort, nr, nc
                    });
                }
            }
        }

        return 0;
    }
}