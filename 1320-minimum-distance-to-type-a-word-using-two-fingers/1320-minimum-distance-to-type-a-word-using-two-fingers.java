class Solution {

    public int minimumDistance(String word) {

        int n = word.length();

        // dp[left][right]
        // Position of the two fingers
        int[][] dp = new int[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        /*
         * Initially both fingers can be anywhere,
         * so we start with the first character typed
         * by one finger.
         */
        int first = word.charAt(0) - 'A';

        for (int i = 0; i < 26; i++) {
            dp[first][i] = 0;
            dp[i][first] = 0;
        }

        for (int i = 1; i < n; i++) {

            int current = word.charAt(i) - 'A';

            int[][] next = new int[26][26];

            for (int a = 0; a < 26; a++) {
                for (int b = 0; b < 26; b++) {
                    next[a][b] = Integer.MAX_VALUE;
                }
            }

            for (int a = 0; a < 26; a++) {

                for (int b = 0; b < 26; b++) {

                    if (dp[a][b] == Integer.MAX_VALUE) {
                        continue;
                    }

                    // Finger 1 types current
                    int cost1 =
                        dp[a][b] + distance(a, current);

                    next[current][b] =
                        Math.min(
                            next[current][b],
                            cost1
                        );

                    // Finger 2 types current
                    int cost2 =
                        dp[a][b] + distance(b, current);

                    next[a][current] =
                        Math.min(
                            next[a][current],
                            cost2
                        );
                }
            }

            dp = next;
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                answer = Math.min(answer, dp[i][j]);
            }
        }

        return answer;
    }

    private int distance(int a, int b) {

        int r1 = a / 6;
        int c1 = a % 6;

        int r2 = b / 6;
        int c2 = b % 6;

        return Math.abs(r1 - r2)
             + Math.abs(c1 - c2);
    }
}