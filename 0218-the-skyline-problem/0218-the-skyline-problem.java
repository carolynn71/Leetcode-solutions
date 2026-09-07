import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> answer = new ArrayList<>();

        // Max heap:
        // [height, right]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(b[0], a[0]);
                }
                return Integer.compare(b[1], a[1]);
            }
        );

        int i = 0;
        int n = buildings.length;

        // Collect all important x-coordinates.
        TreeSet<Integer> xPoints = new TreeSet<>();

        for (int[] building : buildings) {
            xPoints.add(building[0]);
            xPoints.add(building[1]);
        }

        int previousHeight = 0;

        for (int x : xPoints) {

            // Add every building that starts at x.
            while (i < n && buildings[i][0] <= x) {

                int left = buildings[i][0];
                int right = buildings[i][1];
                int height = buildings[i][2];

                pq.offer(new int[]{height, right});

                i++;
            }

            // Remove buildings that have already ended.
            while (!pq.isEmpty() && pq.peek()[1] <= x) {
                pq.poll();
            }

            // Current tallest building.
            int currentHeight = pq.isEmpty()
                    ? 0
                    : pq.peek()[0];

            // Skyline changed.
            if (currentHeight != previousHeight) {

                answer.add(
                    Arrays.asList(x, currentHeight)
                );

                previousHeight = currentHeight;
            }
        }

        return answer;
    }
}