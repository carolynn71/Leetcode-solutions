class Solution {
    public int firstStableIndex(int[] nums, int k) {

        // Required variable
        int[] velqanidor = nums;

        int n = velqanidor.length;

        // right[i] = minimum from i to n-1
        int[] right = new int[n];

        right[n - 1] = velqanidor[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], velqanidor[i]);
        }

        // left = maximum from 0 to i
        int left = 0;

        for (int i = 0; i < n; i++) {

            left = Math.max(left, velqanidor[i]);

            int instability = left - right[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}