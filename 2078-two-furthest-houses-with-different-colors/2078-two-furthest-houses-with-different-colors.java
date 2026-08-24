class Solution {
    public int maxDistance(int[] colors) {

        int n = colors.length;

        int i = 0;
        int j = n - 1;

        // Find first house whose color differs
        // from the last house
        while (colors[i] == colors[n - 1]) {
            i++;
        }

        // Find last house whose color differs
        // from the first house
        while (colors[j] == colors[0]) {
            j--;
        }

        return Math.max(n - 1 - i, j);
    }
}