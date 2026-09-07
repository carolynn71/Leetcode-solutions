class Solution {
    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] length = new int[n];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            length[i] = 1;
            count[i] = 1;
        }

        int maxLength = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                // Can nums[i] come after nums[j]?
                if (nums[j] < nums[i]) {

                    // Found a longer LIS ending at i
                    if (length[j] + 1 > length[i]) {

                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    }

                    // Found another LIS of the same length
                    else if (length[j] + 1 == length[i]) {

                        count[i] += count[j];
                    }
                }
            }

            // Found a new overall maximum length
            if (length[i] > maxLength) {

                maxLength = length[i];
                answer = count[i];
            }

            // Another endpoint with the same maximum length
            else if (length[i] == maxLength) {

                answer += count[i];
            }
        }

        return answer;
    }
}