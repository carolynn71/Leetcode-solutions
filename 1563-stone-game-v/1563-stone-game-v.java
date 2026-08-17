class Solution {

    private int[] prefix;
    private Integer[][] dp;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        prefix = new int[n + 1];
        dp = new Integer[n][n];

        // Prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    private int solve(int[] stones, int left, int right) {

        // Only one stone → cannot split
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != null) {
            return dp[left][right];
        }

        int answer = 0;

        // Try every possible split
        for (int mid = left; mid < right; mid++) {

            int leftSum =
                prefix[mid + 1] - prefix[left];

            int rightSum =
                prefix[right + 1] - prefix[mid + 1];

            if (leftSum < rightSum) {

                // Bob removes right part
                answer = Math.max(
                    answer,
                    leftSum + solve(stones, left, mid)
                );

            } else if (leftSum > rightSum) {

                // Bob removes left part
                answer = Math.max(
                    answer,
                    rightSum + solve(stones, mid + 1, right)
                );

            } else {

                // Equal → Alice chooses either side
                answer = Math.max(
                    answer,
                    Math.max(
                        leftSum + solve(stones, left, mid),
                        rightSum + solve(stones, mid + 1, right)
                    )
                );
            }
        }

        return dp[left][right] = answer;
    }
}