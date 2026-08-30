import java.util.*;

class Solution {
    public int minimumCost(int[] cost) {

        Arrays.sort(cost);

        int total = 0;
        int n = cost.length;

        // Start from the most expensive candy
        for (int i = n - 1; i >= 0; i--) {

            // Every 3rd candy is free
            if ((n - 1 - i) % 3 != 2) {
                total += cost[i];
            }
        }

        return total;
    }
}