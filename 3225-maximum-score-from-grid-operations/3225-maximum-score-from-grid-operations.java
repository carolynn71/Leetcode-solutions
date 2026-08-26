class Solution {
    public long maximumScore(int[][] grid) {

        int n = grid.length;

        // prefix[col][i] = sum of first i cells in column col
        long[][] prefix = new long[n][n + 1];

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                prefix[col][row + 1] =
                    prefix[col][row] + grid[row][col];
            }
        }

        /*
         * prevPick[i]:
         * best score after previous column,
         * where its boundary is at i.
         *
         * prevSkip[i]:
         * best score when the previous column
         * does not contribute.
         */
        long[] prevPick = new long[n + 1];
        long[] prevSkip = new long[n + 1];

        for (int col = 1; col < n; col++) {

            long[] currPick = new long[n + 1];
            long[] currSkip = new long[n + 1];

            for (int curr = 0; curr <= n; curr++) {

                for (int prev = 0; prev <= n; prev++) {

                    if (curr > prev) {

                        // Previous column contributes
                        long score =
                            prefix[col - 1][curr]
                            - prefix[col - 1][prev];

                        currPick[curr] = Math.max(
                            currPick[curr],
                            prevSkip[prev] + score
                        );

                        currSkip[curr] = Math.max(
                            currSkip[curr],
                            prevSkip[prev] + score
                        );

                    } else {

                        // Current column contributes
                        long score =
                            prefix[col][prev]
                            - prefix[col][curr];

                        currPick[curr] = Math.max(
                            currPick[curr],
                            prevPick[prev] + score
                        );

                        currSkip[curr] = Math.max(
                            currSkip[curr],
                            prevPick[prev]
                        );
                    }
                }
            }

            prevPick = currPick;
            prevSkip = currSkip;
        }

        long answer = 0;

        for (long value : prevPick) {
            answer = Math.max(answer, value);
        }

        return answer;
    }
}