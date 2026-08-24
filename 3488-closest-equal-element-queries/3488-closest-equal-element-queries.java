import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {

        int n = nums.length;
        int m = 2 * n;

        int[] dist = new int[m];
        Arrays.fill(dist, m);

        // Find closest same element on the left
        Map<Integer, Integer> left = new HashMap<>();

        for (int i = 0; i < m; i++) {

            int value = nums[i % n];

            if (left.containsKey(value)) {
                dist[i] = Math.min(
                    dist[i],
                    i - left.get(value)
                );
            }

            left.put(value, i);
        }

        // Find closest same element on the right
        Map<Integer, Integer> right = new HashMap<>();

        for (int i = m - 1; i >= 0; i--) {

            int value = nums[i % n];

            if (right.containsKey(value)) {
                dist[i] = Math.min(
                    dist[i],
                    right.get(value) - i
                );
            }

            right.put(value, i);
        }

        // Combine the two copies
        for (int i = 0; i < n; i++) {
            dist[i] = Math.min(
                dist[i],
                dist[i + n]
            );
        }

        // Answer queries
        List<Integer> ans = new ArrayList<>();

        for (int q : queries) {

            if (dist[q] >= n) {
                ans.add(-1);
            } else {
                ans.add(dist[q]);
            }
        }

        return ans;
    }
}