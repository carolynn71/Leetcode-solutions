import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        // If there are no restrictions,
        // heights can increase by 1 from building 1.
        if (restrictions.length == 0) {
            return n - 1;
        }

        // Add building 1 with height 0
        int[][] r = new int[restrictions.length + 1][2];

        for (int i = 0; i < restrictions.length; i++) {
            r[i] = restrictions[i].clone();
        }

        r[restrictions.length] = new int[]{1, 0};

        // Sort by building number
        Arrays.sort(r, (a, b) -> Integer.compare(a[0], b[0]));

        // Remove duplicate building 1 if present
        List<int[]> list = new ArrayList<>();

        for (int[] x : r) {
            if (!list.isEmpty() &&
                list.get(list.size() - 1)[0] == x[0]) {

                list.get(list.size() - 1)[1] =
                    Math.min(list.get(list.size() - 1)[1], x[1]);

            } else {
                list.add(x);
            }
        }

        // Left → Right
        for (int i = 1; i < list.size(); i++) {

            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);

            curr[1] = Math.min(
                curr[1],
                prev[1] + (curr[0] - prev[0])
            );
        }

        // Right → Left
        for (int i = list.size() - 2; i >= 0; i--) {

            int[] curr = list.get(i);
            int[] next = list.get(i + 1);

            curr[1] = Math.min(
                curr[1],
                next[1] + (next[0] - curr[0])
            );
        }

        int maxHeight = 0;

        // Check the maximum height between restrictions
        for (int i = 0; i < list.size() - 1; i++) {

            int[] left = list.get(i);
            int[] right = list.get(i + 1);

            int distance = right[0] - left[0];

            int h1 = left[1];
            int h2 = right[1];

            /*
             * Maximum height between two restrictions.
             */
            int peak =
                (h1 + h2 + distance) / 2;

            maxHeight = Math.max(maxHeight, peak);
        }

        // Buildings after the last restriction
        int[] last = list.get(list.size() - 1);

        maxHeight = Math.max(
            maxHeight,
            last[1] + (n - last[0])
        );

        return maxHeight;
    }
}