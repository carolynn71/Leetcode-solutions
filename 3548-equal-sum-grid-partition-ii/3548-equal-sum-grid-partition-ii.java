import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {
        return check(grid) || check(transpose(grid));
    }

    private boolean check(int[][] g) {

        int m = g.length;
        int n = g[0].length;

        long topSum = 0;
        long bottomSum = 0;

        Map<Long, Integer> top = new HashMap<>();
        Map<Long, Integer> bottom = new HashMap<>();

        // Initially everything is in bottom
        for (int[] row : g) {
            for (int x : row) {
                bottomSum += x;
                bottom.put((long) x,
                    bottom.getOrDefault((long) x, 0) + 1);
            }
        }

        // Try every horizontal cut
        for (int i = 0; i < m - 1; i++) {

            for (int x : g[i]) {

                topSum += x;
                bottomSum -= x;

                top.put((long) x,
                    top.getOrDefault((long) x, 0) + 1);

                bottom.put((long) x,
                    bottom.getOrDefault((long) x, 0) - 1);
            }

            // Already equal
            if (topSum == bottomSum) {
                return true;
            }

            long diff;

            if (topSum < bottomSum) {

                // Need to remove diff from bottom
                diff = bottomSum - topSum;

                if (bottom.getOrDefault(diff, 0) > 0
                        && canRemoveBottom(g, i, diff)) {
                    return true;
                }

            } else {

                // Need to remove diff from top
                diff = topSum - bottomSum;

                if (top.getOrDefault(diff, 0) > 0
                        && canRemoveTop(g, i, diff)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canRemoveBottom(
            int[][] g, int cut, long value) {

        int m = g.length;
        int n = g[0].length;

        // If bottom has more than one row and
        // more than one column, removing an edge
        // cell keeps it connected.
        if (m - cut - 1 > 1 && n > 1) {
            return true;
        }

        // Bottom has exactly one row.
        // Only an endpoint can be removed.
        if (m - cut - 1 == 1) {

            return g[cut + 1][0] == value
                || g[cut + 1][n - 1] == value;
        }

        // One-column case
        if (n == 1) {

            return g[cut + 1][0] == value
                || g[m - 1][0] == value;
        }

        return false;
    }

    private boolean canRemoveTop(
            int[][] g, int cut, long value) {

        int n = g[0].length;

        // More than one row and column
        if (cut + 1 > 1 && n > 1) {
            return true;
        }

        // Top has exactly one row.
        // Only an endpoint can be removed.
        if (cut == 0) {

            return g[0][0] == value
                || g[0][n - 1] == value;
        }

        // One-column case
        if (n == 1) {

            return g[0][0] == value
                || g[cut][0] == value;
        }

        return false;
    }

    private int[][] transpose(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = grid[i][j];
            }
        }

        return result;
    }
}