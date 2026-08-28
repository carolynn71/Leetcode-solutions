class Solution {
    public boolean isGood(int[] nums) {

        int n = nums.length - 1;

        int[] freq = new int[n + 1];

        for (int num : nums) {

            if (num < 1 || num > n) {
                return false;
            }

            freq[num]++;
        }

        // 1 to n-1 appear once
        for (int i = 1; i < n; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }

        // n appears twice
        return freq[n] == 2;
    }
}