class Solution {
    public boolean uniformArray(int[] nums1) {

        int min = Integer.MAX_VALUE;

        // Find minimum element
        for (int x : nums1) {
            min = Math.min(min, x);
        }

        // If minimum is odd, always possible
        if (min % 2 == 1) {
            return true;
        }

        // Minimum is even.
        // Therefore every element must be even.
        for (int x : nums1) {
            if (x % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}