import java.util.*;

class Solution {

    int[] parent;

    public int minimumHammingDistance(
            int[] source,
            int[] target,
            int[][] allowedSwaps) {

        int n = source.length;

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Build connected components
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }

        // Count source values in each component
        Map<Integer, Map<Integer, Integer>> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int root = find(i);

            freq
                .computeIfAbsent(root, k -> new HashMap<>())
                .merge(source[i], 1, Integer::sum);
        }

        int answer = 0;

        // Try to match target values
        for (int i = 0; i < n; i++) {

            int root = find(i);

            Map<Integer, Integer> map = freq.get(root);

            int count = map.getOrDefault(target[i], 0);

            if (count > 0) {
                map.put(target[i], count - 1);
            } else {
                answer++;
            }
        }

        return answer;
    }

    private int find(int x) {

        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    private void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}