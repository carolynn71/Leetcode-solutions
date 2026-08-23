import java.util.*;

class Solution {

    public long minimumTotalDistance(
            List<Integer> robot,
            int[][] factory) {

        // Sort robots
        Collections.sort(robot);

        // Sort factories by position
        Arrays.sort(factory, (a, b) ->
                Integer.compare(a[0], b[0]));

        int n = robot.size();

        long[][] dp = new long[factory.length + 1][n + 1];

        long INF = Long.MAX_VALUE / 4;

        // Initialize
        for (int i = 0; i <= factory.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        /*
         * Process factories one by one.
         */
        for (int i = 1; i <= factory.length; i++) {

            int position = factory[i - 1][0];
            int capacity = factory[i - 1][1];

            for (int j = 0; j <= n; j++) {

                // Don't use this factory
                dp[i][j] = dp[i - 1][j];

                long distance = 0;

                /*
                 * Assign k robots to this factory.
                 */
                for (int k = 1;
                     k <= capacity && k <= j;
                     k++) {

                    int robotIndex = j - k;

                    distance += Math.abs(
                            (long) robot.get(robotIndex)
                                    - position
                    );

                    if (dp[i - 1][j - k] != INF) {

                        dp[i][j] = Math.min(
                                dp[i][j],
                                dp[i - 1][j - k]
                                        + distance
                        );
                    }
                }
            }
        }

        return dp[factory.length][n];
    }
}