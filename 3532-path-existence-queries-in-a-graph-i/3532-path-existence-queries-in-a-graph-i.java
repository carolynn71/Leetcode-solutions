class Solution {
    public boolean[] pathExistenceQueries(
            int n,
            int[] nums,
            int maxDiff,
            int[][] queries) {

        int[] group = new int[n];

        int currentGroup = 0;
        group[0] = 0;

        // Build connected components
        for (int i = 1; i < n; i++) {

            if (nums[i] - nums[i - 1] > maxDiff) {
                currentGroup++;
            }

            group[i] = currentGroup;
        }

        // Answer queries
        boolean[] answer = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            answer[i] = group[u] == group[v];
        }

        return answer;
    }
}