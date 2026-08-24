import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {

        int n = nums.length;
        int ans = n + 1;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            // Check whether some previous number
            // reverses to nums[i]
            if (map.containsKey(nums[i])) {
                ans = Math.min(ans, i - map.get(nums[i]));
            }

            // Store reverse(nums[i]) and its latest index
            map.put(reverse(nums[i]), i);
        }

        return ans == n + 1 ? -1 : ans;
    }

    private int reverse(int x) {

        int rev = 0;

        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return rev;
    }
}