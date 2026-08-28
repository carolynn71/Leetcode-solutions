class Solution {
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is strictly to the right
                left = mid + 1;
            }
            else if (nums[mid] < nums[right]) {
                // Minimum is at mid or to the left
                right = mid;
            }
            else {
                // nums[mid] == nums[right]
                // We cannot determine the side,
                // so safely discard right.
                right--;
            }
        }

        return nums[left];
    }
}