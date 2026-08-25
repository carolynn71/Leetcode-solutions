class Solution {

    int m, n;
    int[][] grid;
    boolean[][] visited;

    // Directions: up, right, down, left
    int[][] dir = {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };

    // For each street type:
    // 0 = up, 1 = right, 2 = down, 3 = left
    int[][] connections = {
        {},
        {3, 1},       // type 1: left, right
        {0, 2},       // type 2: up, down
        {3, 2},       // type 3: left, down
        {1, 2},       // type 4: right, down
        {3, 0},       // type 5: left, up
        {1, 0}        // type 6: right, up
    };

    public boolean hasValidPath(int[][] grid) {

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];

        return dfs(0, 0);
    }

    private boolean dfs(int r, int c) {

        if (r == m - 1 && c == n - 1) {
            return true;
        }

        visited[r][c] = true;

        int type = grid[r][c];

        for (int d : connections[type]) {

            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (nr < 0 || nr >= m ||
                nc < 0 || nc >= n) {
                continue;
            }

            if (visited[nr][nc]) {
                continue;
            }

            // Check that the neighboring street
            // connects back to the current cell.
            if (canConnect(grid[nr][nc], opposite(d))) {

                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }

        return false;
    }

    private int opposite(int d) {
        return (d + 2) % 4;
    }

    private boolean canConnect(int type, int direction) {

        for (int d : connections[type]) {
            if (d == direction) {
                return true;
            }
        }

        return false;
    }
}