class Solution {

    public int maximumAmount(int[][] coins) {

        int m = coins.length;
        int n = coins[0].length;

        // dp[i][j][k]
        // k = number of robbers neutralized
        int[][][] dp = new int[m][n][3];

        // Initialize with a very small value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE / 2;
                }
            }
        }

        // Starting cell
        dp[0][0][0] = coins[0][0];

        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
        }

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                for (int k = 0; k <= 2; k++) {

                    // Best value from top
                    int best = Integer.MIN_VALUE / 2;

                    if (i > 0) {
                        best = Math.max(best, dp[i - 1][j][k]);
                    }

                    // Best value from left
                    if (j > 0) {
                        best = Math.max(best, dp[i][j - 1][k]);
                    }

                    // Don't neutralize current cell
                    dp[i][j][k] =
                        Math.max(
                            dp[i][j][k],
                            best + coins[i][j]
                        );

                    // Neutralize current robber
                    if (coins[i][j] < 0 && k > 0) {

                        int previous = Integer.MIN_VALUE / 2;

                        if (i > 0) {
                            previous =
                                Math.max(
                                    previous,
                                    dp[i - 1][j][k - 1]
                                );
                        }

                        if (j > 0) {
                            previous =
                                Math.max(
                                    previous,
                                    dp[i][j - 1][k - 1]
                                );
                        }

                        dp[i][j][k] =
                            Math.max(
                                dp[i][j][k],
                                previous
                            );
                    }
                }
            }
        }

        return Math.max(
            dp[m - 1][n - 1][0],
            Math.max(
                dp[m - 1][n - 1][1],
                dp[m - 1][n - 1][2]
            )
        );
    }
}