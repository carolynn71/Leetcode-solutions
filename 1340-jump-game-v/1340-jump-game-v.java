class Solution {

    public int maxJumps(int[] arr, int d) {

        int n = arr.length;
        int[] dp = new int[n];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dfs(arr, d, i, dp));
        }

        return answer;
    }

    private int dfs(int[] arr, int d, int i, int[] dp) {

        if (dp[i] != 0) {
            return dp[i];
        }

        // We can always stay at the current index
        dp[i] = 1;

        // Jump LEFT
        for (int j = i - 1;
             j >= Math.max(0, i - d);
             j--) {

            // Cannot jump over this index
            if (arr[j] >= arr[i]) {
                break;
            }

            dp[i] = Math.max(
                dp[i],
                1 + dfs(arr, d, j, dp)
            );
        }

        // Jump RIGHT
        for (int j = i + 1;
             j <= Math.min(arr.length - 1, i + d);
             j++) {

            // Cannot jump over this index
            if (arr[j] >= arr[i]) {
                break;
            }

            dp[i] = Math.max(
                dp[i],
                1 + dfs(arr, d, j, dp)
            );
        }

        return dp[i];
    }
}