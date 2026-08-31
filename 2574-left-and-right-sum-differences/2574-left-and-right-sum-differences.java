class Solution {
    public int[] leftRightDifference(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        int totalSum = 0;

        // Find total sum
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < n; i++) {

            // Remove current element
            // to get right-side sum
            totalSum -= nums[i];

            int rightSum = totalSum;

            answer[i] = Math.abs(leftSum - rightSum);

            // Add current element to left side
            leftSum += nums[i];
        }

        return answer;
    }
}