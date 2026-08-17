class Solution {

    public boolean canPartitionGrid(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        // Calculate total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        // Equal halves require an even total
        if (total % 2 != 0) {
            return false;
        }

        // Check horizontal cuts
        long sum = 0;

        for (int i = 0; i < m - 1; i++) {

            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
            }

            if (sum * 2 == total) {
                return true;
            }
        }

        // Check vertical cuts
        sum = 0;

        for (int j = 0; j < n - 1; j++) {

            for (int i = 0; i < m; i++) {
                sum += grid[i][j];
            }

            if (sum * 2 == total) {
                return true;
            }
        }

        return false;
    }
}