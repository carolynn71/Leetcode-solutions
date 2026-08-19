import java.util.*;

class Solution {

    private int[][] robots;
    private int[] walls;
    private Integer[][] dp;
    private int n;

    public int maxWalls(int[] robot, int[] distance, int[] wall) {

        n = robot.length;

        robots = new int[n][2];

        for (int i = 0; i < n; i++) {
            robots[i][0] = robot[i];
            robots[i][1] = distance[i];
        }

        // Sort robots by position
        Arrays.sort(robots, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        // Sort walls
        walls = wall.clone();
        Arrays.sort(walls);

        dp = new Integer[n][2];

        return dfs(n - 1, 1);
    }

    /*
     * i = current robot
     *
     * nextDir:
     * 0 -> next robot shoots LEFT
     * 1 -> next robot shoots RIGHT
     */
    private int dfs(int i, int nextDir) {

        if (i < 0) {
            return 0;
        }

        if (dp[i][nextDir] != null) {
            return dp[i][nextDir];
        }

        int position = robots[i][0];
        int distance = robots[i][1];

        // --------------------------------
        // OPTION 1: Shoot LEFT
        // --------------------------------

        int left = position - distance;

        // Cannot pass through previous robot
        if (i > 0) {
            left = Math.max(
                left,
                robots[i - 1][0] + 1
            );
        }

        // Walls in [left, position]
        int l = lowerBound(walls, left);
        int r = lowerBound(walls, position + 1);

        int shootLeft =
            dfs(i - 1, 0) + (r - l);


        // --------------------------------
        // OPTION 2: Shoot RIGHT
        // --------------------------------

        int right = position + distance;

        if (i + 1 < n) {

            if (nextDir == 0) {

                // Next robot shoots LEFT.
                // Avoid double-counting walls
                // that next robot can destroy.
                right = Math.min(
                    right,
                    robots[i + 1][0]
                        - robots[i + 1][1]
                        - 1
                );

            } else {

                // Next robot shoots RIGHT.
                // Current bullet stops before it.
                right = Math.min(
                    right,
                    robots[i + 1][0] - 1
                );
            }
        }

        // Walls in [position, right]
        l = lowerBound(walls, position);
        r = lowerBound(walls, right + 1);

        int shootRight =
            dfs(i - 1, 1) + (r - l);


        return dp[i][nextDir] =
            Math.max(shootLeft, shootRight);
    }

    // First index >= target
    private int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}