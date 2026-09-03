class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;
        int ans = 0;

        // Choose starting point
        for (int i = 0; i < n; i++) {

            int count = 0;

            // Extend the subarray
            for (int j = i; j < n; j++) {

                // Count target
                if (nums[j] == target) {
                    count++;
                }

                // Current subarray length
                int length = j - i + 1;

                // target appears more than half
                if (count * 2 > length) {
                    ans++;
                }
            }
        }

        return ans;
    }
}