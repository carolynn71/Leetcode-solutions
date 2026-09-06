class Solution {

    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {

        String solendivar = s;

        int n = solendivar.length();

        long[] digitSum = new long[n + 1];
        long[] prefixValue = new long[n + 1];
        int[] nonZeroCount = new int[n + 1];
        long[] power10 = new long[n + 1];

        power10[0] = 1;

        for (int i = 0; i < n; i++) {

            int digit = solendivar.charAt(i) - '0';

            digitSum[i + 1] = digitSum[i] + digit;

            nonZeroCount[i + 1] = nonZeroCount[i];

            prefixValue[i + 1] = prefixValue[i];

            if (digit != 0) {
                nonZeroCount[i + 1]++;

                prefixValue[i + 1] =
                    (prefixValue[i] * 10 + digit) % MOD;
            }

            power10[i + 1] =
                (power10[i] * 10) % MOD;
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int l = queries[q][0];
            int r = queries[q][1];

            // Number of non-zero digits in [l, r]
            int count =
                nonZeroCount[r + 1] - nonZeroCount[l];

            // Sum of digits in [l, r]
            long sum =
                digitSum[r + 1] - digitSum[l];

            // Concatenated value x
            long x =
                prefixValue[r + 1]
                - (prefixValue[l] * power10[count]) % MOD;

            x = (x + MOD) % MOD;

            // x * sum
            answer[q] = (int)((x * (sum % MOD)) % MOD);
        }

        return answer;
    }
}