import java.util.*;

class Solution {
    public int minJumps(int[] arr) {

        int n = arr.length;

        if (n == 1) {
            return 0;
        }

        // Store all indices having the same value
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>())
               .add(i);
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(0);
        visited[0] = true;

        int jumps = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int i = queue.poll();

                if (i == n - 1) {
                    return jumps;
                }

                // Move left
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                // Move right
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // Jump to all indices with same value
                List<Integer> same = map.get(arr[i]);

                if (same != null) {

                    for (int j : same) {

                        if (!visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }

                    // Important optimization:
                    // Never process this value again.
                    map.remove(arr[i]);
                }
            }

            jumps++;
        }

        return -1;
    }
}