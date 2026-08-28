import java.util.*;

class Solution {
    public boolean canReach(int[] arr, int start) {

        int n = arr.length;

        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {

            int i = queue.poll();

            // Found 0
            if (arr[i] == 0) {
                return true;
            }

            // Jump right
            int right = i + arr[i];

            if (right < n && !visited[right]) {
                visited[right] = true;
                queue.offer(right);
            }

            // Jump left
            int left = i - arr[i];

            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.offer(left);
            }
        }

        return false;
    }
}