class Solution {
    public int[] pivotArray(int[] nums, int pivot) {

        int n = nums.length;
        int[] ans = new int[n];

        int index = 0;

        // 1. Elements smaller than pivot
        for (int num : nums) {
            if (num < pivot) {
                ans[index++] = num;
            }
        }

        // 2. Elements equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                ans[index++] = num;
            }
        }

        // 3. Elements greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                ans[index++] = num;
            }
        }

        return ans;
    }
}