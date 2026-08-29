class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();

        if (s.charAt(n - 1) != '0') {
            return false;
        }

        boolean[] dp = new boolean[n];

        dp[0] = true;

        int reachable = 0;

        for (int i = 1; i < n; i++) {

            // Add index that just entered the jump range
            int add = i - minJump;

            if (add >= 0 && dp[add]) {
                reachable++;
            }

            // Remove index that is no longer in the range
            int remove = i - maxJump - 1;

            if (remove >= 0 && dp[remove]) {
                reachable--;
            }

            // Current index is reachable if:
            // 1. It contains '0'
            // 2. There is at least one reachable
            //    index in [i-maxJump, i-minJump]
            if (s.charAt(i) == '0' && reachable > 0) {
                dp[i] = true;
            }
        }

        return dp[n - 1];
    }
}