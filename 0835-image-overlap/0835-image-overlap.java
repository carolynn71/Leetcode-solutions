import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Store positions of 1s in img1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }

        HashMap<String, Integer> map = new HashMap<>();

        int answer = 0;

        // Try every pair of 1s
        for (int[] a : ones1) {
            for (int[] b : ones2) {

                int dx = a[0] - b[0];
                int dy = a[1] - b[1];

                String key = dx + "," + dy;

                int count = map.getOrDefault(key, 0) + 1;

                map.put(key, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }
}