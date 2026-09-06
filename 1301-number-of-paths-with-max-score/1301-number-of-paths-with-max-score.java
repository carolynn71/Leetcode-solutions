import java.util.*;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();
        int MOD = 1_000_000_007;

        // maxScore[i][j] = maximum score reaching (i, j)
        int[][] maxScore = new int[n][n];

        // ways[i][j] = number of ways to get maxScore[i][j]
        int[][] ways = new int[n][n];

        // -1 means unreachable
        for (int[] row : maxScore) {
            Arrays.fill(row, -1);
        }

        // Start at S
        maxScore[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        // Start from bottom-right and move toward top-left
        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                // Skip S because it is already initialized
                if (i == n - 1 && j == n - 1) {
                    continue;
                }

                // Skip obstacles
                if (board.get(i).charAt(j) == 'X') {
                    continue;
                }

                int best = -1;
                int count = 0;

                // 1. Move down
                if (i + 1 < n && maxScore[i + 1][j] != -1) {

                    if (maxScore[i + 1][j] > best) {
                        best = maxScore[i + 1][j];
                        count = ways[i + 1][j];
                    } else if (maxScore[i + 1][j] == best) {
                        count = (count + ways[i + 1][j]) % MOD;
                    }
                }

                // 2. Move right
                if (j + 1 < n && maxScore[i][j + 1] != -1) {

                    if (maxScore[i][j + 1] > best) {
                        best = maxScore[i][j + 1];
                        count = ways[i][j + 1];
                    } else if (maxScore[i][j + 1] == best) {
                        count = (count + ways[i][j + 1]) % MOD;
                    }
                }

                // 3. Move diagonally down-right
                if (i + 1 < n && j + 1 < n
                        && maxScore[i + 1][j + 1] != -1) {

                    if (maxScore[i + 1][j + 1] > best) {
                        best = maxScore[i + 1][j + 1];
                        count = ways[i + 1][j + 1];
                    } else if (maxScore[i + 1][j + 1] == best) {
                        count = (count + ways[i + 1][j + 1]) % MOD;
                    }
                }

                // No path can reach this cell
                if (best == -1) {
                    continue;
                }

                maxScore[i][j] = best;
                ways[i][j] = count;

                // Add current cell's digit
                char c = board.get(i).charAt(j);

                if (c >= '1' && c <= '9') {
                    maxScore[i][j] += c - '0';
                }
            }
        }

        // E cannot be reached
        if (maxScore[0][0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{
            maxScore[0][0],
            ways[0][0]
        };
    }
}