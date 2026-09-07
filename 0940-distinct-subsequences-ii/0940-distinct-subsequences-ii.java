class Solution {
    private static final int MOD = 1_000_000_007;

    public int distinctSubseqII(String s) {
        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long total = 0;

            for (long x : dp) {
                total = (total + x) % MOD;
            }

            dp[index] = (total + 1) % MOD;
        }

        long answer = 0;

        for (long x : dp) {
            answer = (answer + x) % MOD;
        }

        return (int) answer;
    }
}