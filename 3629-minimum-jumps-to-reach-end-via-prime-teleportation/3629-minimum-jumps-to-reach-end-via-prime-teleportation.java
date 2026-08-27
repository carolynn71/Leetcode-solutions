import java.util.*;

class Solution {

    public int minJumps(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return 0;
        }

        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        // Smallest Prime Factor
        int[] spf = new int[max + 1];

        for (int i = 2; i <= max; i++) {

            if (spf[i] == 0) {

                for (int j = i; j <= max; j += i) {

                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }

        // prime -> indices whose values are divisible by prime
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int x = nums[i];

            while (x > 1) {

                int p = spf[x];

                map.computeIfAbsent(p, k -> new ArrayList<>())
                   .add(i);

                // Remove duplicate prime factors
                while (x % p == 0) {
                    x /= p;
                }
            }
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

                // Prime teleportation
                int value = nums[i];

                // nums[i] itself must be prime
                if (value >= 2 && spf[value] == value) {

                    List<Integer> indices = map.get(value);

                    if (indices != null) {

                        for (int j : indices) {

                            if (!visited[j]) {

                                visited[j] = true;
                                queue.offer(j);
                            }
                        }

                        // Never process this teleport group again
                        map.remove(value);
                    }
                }
            }

            jumps++;
        }

        return -1;
    }
}