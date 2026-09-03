import java.util.*;

class Solution {

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        // dist[i][j] = distance from cell to nearest thief
        int[][] dist = new int[n][n];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        // Multi-source BFS
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] cell = queue.poll();

            int r = cell[0];
            int c = cell[1];

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                dist[nr][nc] = dist[r][c] + 1;

                queue.offer(new int[]{nr, nc});
            }
        }

        // Binary search the answer
        int left = 0;
        int right = 2 * n;

        while (left < right) {

            int mid = (left + right + 1) / 2;

            if (canReach(dist, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean canReach(int[][] dist, int safe) {

        int n = dist.length;

        if (dist[0][0] < safe) {
            return false;
        }

        Queue<int[]> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[n][n];

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] cell = queue.poll();

            int r = cell[0];
            int c = cell[1];

            if (r == n - 1 && c == n - 1) {
                return true;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (dist[nr][nc] < safe) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return false;
    }
}