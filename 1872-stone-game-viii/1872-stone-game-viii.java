class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Convert stones into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // If we take all remaining stones,
        // the score difference is the total sum.
        int best = stones[n - 1];

        // Work backwards
        for (int i = n - 2; i > 0; i--) {

            // Either:
            // 1. Keep the current best
            // 2. Take prefix sum stones[i]
            //    and lose best to the opponent
            best = Math.max(
                best,
                stones[i] - best
            );
        }

        return best;
    }
}