class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {
        int n = static_cast<int>(nums.size());

        // suffixMin[i] = minimum value among nums[i..n-1]
        vector<int> suffixMin(n);
        suffixMin[n - 1] = nums[n - 1];

        // Build the suffix-minimum array from right to left
        for (int i = n - 2; i >= 0; --i) {
            suffixMin[i] = min(suffixMin[i + 1], nums[i]);
        }

        // prefixMax tracks the maximum value among nums[0..i]
        int prefixMax = 0;
        for (int i = 0; i < n; ++i) {
            prefixMax = max(prefixMax, nums[i]);

            // Return the first index where the gap between the
            // running prefix-maximum and the suffix-minimum is within k
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        // No qualifying index found
        return -1;
    }
};
