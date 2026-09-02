class Solution {
    public int zigZagArrays(int n, int l, int r) {

        final long MOD = 1_000_000_007L;

        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        // For arrays of length 1,
        // there is no direction yet.
        for (int i = 0; i < m; i++) {
            up[i] = 1;
            down[i] = 1;
        }

        // Build arrays of length 2 ... n
        for (int len = 2; len <= n; len++) {

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            // Prefix sum of down[]
            long[] prefix = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefix[i + 1] =
                    (prefix[i] + down[i]) % MOD;
            }

            // Suffix sum of up[]
            long[] suffix = new long[m + 1];

            for (int i = m - 1; i >= 0; i--) {
                suffix[i] =
                    (suffix[i + 1] + up[i]) % MOD;
            }

            for (int i = 0; i < m; i++) {

                // Last move is increasing:
                // previous value < current value
                newUp[i] = prefix[i];

                // Last move is decreasing:
                // previous value > current value
                newDown[i] = suffix[i + 1];
            }

            up = newUp;
            down = newDown;
        }

        long answer = 0;

        for (int i = 0; i < m; i++) {
            answer = (answer + up[i] + down[i]) % MOD;
        }

        return (int) answer;
    }
}