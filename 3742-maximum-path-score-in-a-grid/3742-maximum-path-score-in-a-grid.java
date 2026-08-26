class Solution {

    private int[][] grid;
    private Integer[][][] dp;
    private final int INF = 1 << 30;

    public int maxPathScore(int[][] grid, int k) {

        this.grid = grid;

        int m = grid.length;
        int n = grid[0].length;

        dp = new Integer[m][n][k + 1];

        int ans = dfs(m - 1, n - 1, k);

        return ans < 0 ? -1 : ans;
    }

    private int dfs(int i, int j, int k) {

        // Outside grid or cost exceeded
        if (i < 0 || j < 0 || k < 0) {
            return -INF;
        }

        // Starting cell
        if (i == 0 && j == 0) {
            return 0;
        }

        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }

        int value = grid[i][j];

        // Non-zero cell costs 1
        int newK = k;

        if (value > 0) {
            newK--;
        }

        int fromTop = dfs(i - 1, j, newK);
        int fromLeft = dfs(i, j - 1, newK);

        int best = value + Math.max(fromTop, fromLeft);

        return dp[i][j][k] = best;
    }
}