class Solution {

    public int maxProductPath(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long[][] min = new long[m][n];
        long[][] max = new long[m][n];

        min[0][0] = grid[0][0];
        max[0][0] = grid[0][0];

        // First column
        for (int i = 1; i < m; i++) {

            min[i][0] = min[i - 1][0] * grid[i][0];
            max[i][0] = max[i - 1][0] * grid[i][0];
        }

        // First row
        for (int j = 1; j < n; j++) {

            min[0][j] = min[0][j - 1] * grid[0][j];
            max[0][j] = max[0][j - 1] * grid[0][j];
        }

        // Remaining cells
        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                long value = grid[i][j];

                long a = min[i - 1][j] * value;
                long b = max[i - 1][j] * value;
                long c = min[i][j - 1] * value;
                long d = max[i][j - 1] * value;

                min[i][j] = Math.min(
                    Math.min(a, b),
                    Math.min(c, d)
                );

                max[i][j] = Math.max(
                    Math.max(a, b),
                    Math.max(c, d)
                );
            }
        }

        long answer = max[m - 1][n - 1];

        if (answer < 0)
            return -1;

        return (int)(answer % 1_000_000_007);
    }
}