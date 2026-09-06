import java.util.*;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {

        // Sort by start ascending.
        // If starts are equal, sort by end descending.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int count = 0;
        int maxEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {

            int end = interval[1];

            // Current interval is NOT covered
            if (end > maxEnd) {
                count++;
                maxEnd = end;
            }
        }

        return count;
    }
}