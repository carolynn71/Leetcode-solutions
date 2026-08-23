class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {

        int n = words.length;
        int ans = n;

        for (int i = 0; i < n; i++) {

            if (words[i].equals(target)) {

                int distance = Math.abs(i - startIndex);

                int circularDistance =
                    Math.min(distance, n - distance);

                ans = Math.min(ans, circularDistance);
            }
        }

        return ans == n ? -1 : ans;
    }
}