import java.util.*;

class Solution {
    public long[] distance(int[] nums) {

        int n = nums.length;
        long[] ans = new long[n];

        // Store indices for each number
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Process each group of equal values
        for (List<Integer> list : map.values()) {

            long prefixSum = 0;

            for (int j = 0; j < list.size(); j++) {

                int index = list.get(j);

                // Distance from all previous indices
                ans[index] +=
                    (long) index * j - prefixSum;

                prefixSum += index;
            }

            long suffixSum = 0;

            for (int j = list.size() - 1; j >= 0; j--) {

                int index = list.get(j);

                int countAfter = list.size() - 1 - j;

                // Distance from all later indices
                ans[index] +=
                    suffixSum - (long) index * countAfter;

                suffixSum += index;
            }
        }

        return ans;
    }
}