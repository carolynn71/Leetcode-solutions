import java.util.*;

class Solution {
    public int heightChecker(int[] heights) {

        // Make a copy so we don't change the original array
        int[] expected = heights.clone();

        // Sort to get the expected order
        Arrays.sort(expected);

        int count = 0;

        // Count positions that are different
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                count++;
            }
        }

        return count;
    }
}