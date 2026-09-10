import java.util.*;

class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {

        int n = passingFees.length;
        int INF = 1 << 30;

        int[][] dp = new int[maxTime + 1][n];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // Starting city fee
        dp[0][0] = passingFees[0];

        // Try every possible amount of time
        for (int time = 1; time <= maxTime; time++) {

            // Try every road
            for (int[] edge : edges) {

                int u = edge[0];
                int v = edge[1];
                int roadTime = edge[2];

                if (roadTime <= time) {

                    // u -> v
                    if (dp[time - roadTime][u] != INF) {
                        dp[time][v] = Math.min(
                            dp[time][v],
                            dp[time - roadTime][u] + passingFees[v]
                        );
                    }

                    // v -> u
                    if (dp[time - roadTime][v] != INF) {
                        dp[time][u] = Math.min(
                            dp[time][u],
                            dp[time - roadTime][v] + passingFees[u]
                        );
                    }
                }
            }
        }

        int answer = INF;

        // Destination can be reached in ANY time <= maxTime
        for (int time = 0; time <= maxTime; time++) {
            answer = Math.min(answer, dp[time][n - 1]);
        }

        return answer == INF ? -1 : answer;
    }
}