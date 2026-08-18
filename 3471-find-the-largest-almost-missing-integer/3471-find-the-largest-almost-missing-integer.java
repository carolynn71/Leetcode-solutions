import java.util.*;

class Solution {

    public int largestInteger(int[] nums, int k) {

        int n = nums.length;

        // If k == n, there is only one subarray:
        // the entire array.
        if (k == n) {
            int max = 0;

            for (int num : nums) {
                max = Math.max(max, num);
            }

            return max;
        }

        // Count frequency of every number
        int[] count = new int[51];

        for (int num : nums) {
            count[num]++;
        }

        // If k == 1, every element itself is a subarray.
        if (k == 1) {

            int answer = -1;

            for (int num : nums) {
                if (count[num] == 1) {
                    answer = Math.max(answer, num);
                }
            }

            return answer;
        }

        // For 1 < k < n, only the first and last
        // elements can appear in exactly one k-subarray.
        int answer = -1;

        if (count[nums[0]] == 1) {
            answer = Math.max(answer, nums[0]);
        }

        if (count[nums[n - 1]] == 1) {
            answer = Math.max(answer, nums[n - 1]);
        }

        return answer;
    }
}