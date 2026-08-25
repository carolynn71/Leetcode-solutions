class Solution {

    int m, n;
    char[][] grid;
    boolean[][] visited;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public boolean containsCycle(char[][] grid) {

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                if (!visited[r][c]) {

                    if (dfs(r, c, -1, -1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(int r, int c, int parentR, int parentC) {

        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            // Outside grid
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }

            // Different character
            if (grid[nr][nc] != grid[r][c]) {
                continue;
            }

            // Don't immediately go back to parent
            if (nr == parentR && nc == parentC) {
                continue;
            }

            // Visited same-character cell = cycle
            if (visited[nr][nc]) {
                return true;
            }

            if (dfs(nr, nc, r, c)) {
                return true;
            }
        }

        return false;
    }
}